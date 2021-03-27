package site.lanmushan.groovyscript;

import com.alibaba.fastjson.JSONObject;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.springframework.util.StringUtils;
import site.lanmushan.framework.cypher.md5.MD5Util;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.SimpleBindings;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Administrator
 */
@Slf4j
public class GroovyScriptUtil {
    private static GroovyClassLoader classLoader = new GroovyClassLoader();
    private static final GroovyScriptEngineFactory scriptEngineFactory = new GroovyScriptEngineFactory();


    /**
     * 转换内容
     * 这个方法很慢
     *
     * @param content
     * @return
     */
    public static String transform(String content, Map<String, Object> data) {
        try {
            if (content == null || StringUtils.isEmpty(content.trim())) {
                return null;
            }
            String str = content.replaceAll("\\\"", "\\\\\"").replaceAll("\n", "");
            StringBuilder sb = new StringBuilder();
            sb.append("String str=\"")
                    .append(str)
                    .append("\";").append("return str;");
            ScriptEngine scriptEngine = scriptEngineFactory.getScriptEngine();
            Bindings bindings = new SimpleBindings();
            if (data != null && !data.isEmpty()) {
                bindings.putAll(data);
            }
            String result = (String) scriptEngine.eval(sb.toString(), bindings);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 计算表达式
     *
     * @param content
     * @param data
     * @return
     */
    public static Object execute(String content,Object ctxObj, Map<String, Object> data, Class<?> classz) {
        String result = execute(content,ctxObj, data);
        if (Boolean.class.isAssignableFrom(classz)) {
            return Boolean.parseBoolean(result);
        }
        if (String.class.isAssignableFrom(classz)) {
            return result;
        }
        return result;
    }

    /**
     * 计算表达式
     *
     * @param content
     * @param data
     * @return
     */
    public static String execute(String content,Object ctxObject, Map<String, Object> data) {
        String str = "\"${" + content + "}\"";
        return transformUseClass(str,ctxObject, data);
    }

    /**
     * 这个性能比较高，就是看着脑壳有点儿疼
     * 第一次比较慢
     * 后面比较快
     *
     * @param content
     * @param data
     * @return
     */
    public static String transformUseClass(String content, Object ctxObj,Map<String, Object> data) {
        try {
            //简单判断，后面优化
            if(content==null||"\n\n".equals(content)||"".equals(content))
            {
                return null;
            }
            String md5 = MD5Util.createMD532(content);
            StringBuilder script = new StringBuilder();
            //content = content.replaceAll("\\\"", "\\\\\"").replaceAll("\n", "");
            String className = "GroovyTransform" + md5;
            Class<?> classz = loadClass(className, null);
            if (classz == null) {
                script.append("class ").append(className)
                        .append("\n{\n")
                        .append("private Map<String, Object> map;\n")
                        .append(className)
                        .append("(Map<String, Object> map) \n{ this.map = map }\n")
                        .append("def getProperty(String name) {map.get(name)}\n")
                        .append("def Object doHandler(Object ctx) {\n");
                script.append("String str=")
                        .append(content)
                        .append(";").append("return str;")
                        .append("}}");
                log.info("脚本{}",script.toString());
                classz = loadClass(className, script.toString());
            }
            Constructor<?> constructor = classz.getConstructor(Map.class);
            GroovyObject obj = (GroovyObject) constructor.newInstance(data);
            return (String) obj.invokeMethod("doHandler", ctxObj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object newInstance(String scirpt) {
        try {
            Class clazz = classLoader.parseClass(scirpt);
            Object o = clazz.newInstance();
            return o;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static Object doExecuteByClass(String className, String script, String methodName, Object... data) {
        GroovyObject obj = (GroovyObject) newInstance(className, script);
        return obj.invokeMethod(methodName, data);
    }


    /**
     * TODO 这里还可以优化一下，缓存obj，但是要控制一下
     *
     * @param className
     * @param script
     * @return
     */
    public static Object newInstance(String className, String script) {
        try {
            Class clazz = loadClass(className, script);
            Object o = clazz.newInstance();
            return o;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private static Class loadClass(String className, String script) {
        Class clazz = null;
        try {
            clazz = classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
        }
        if (script == null) {
            return null;
        }
        if (clazz == null) {
            clazz = classLoader.parseClass(script);
        }
        return clazz;
    }

    public static void main(String[] args) throws Exception {

        JSONObject json = new JSONObject();
        json.put("name", "${name}");
        Map<String, Object> data = new HashMap<>();
        data.put("name", "张三");
        System.out.println(GroovyScriptUtil.transformUseClass(JSONObject.toJSONString(json.toJSONString()),null, data));
        Object obj = GroovyScriptUtil.execute("1!=1", data,null, Boolean.class);
        System.out.println(obj);


    }
//public static void main(String[] args) {
//        JSONObject json=new JSONObject();
//        json.put("userId",1);
//        json.put("deptId",1);
//        json.put("nickName","别名");
//        json.put("roleCodes", Arrays.asList("admin","op"));
//        String str="class DataScopeDynamicGroovy {\n" +
//                "    def test(){\n" +
//                "        return \"xxxxxxxx\"\n" +
//                "    }\n" +
//                "\n" +
//                "}";
//        System.out.println(  GroovyScriptUtil.doExecuteByClass("DataScopeDynamicGroovy",str,"test",null));
//}

}
