package site.lanmushan.groovyscript;

import com.alibaba.fastjson.JSONObject;
import groovy.json.StringEscapeUtils;
import groovy.lang.GroovyClassLoader;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.springframework.util.StringUtils;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.SimpleBindings;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Administrator
 */
@Slf4j
public class GroovyScriptUtil {
    private static GroovyClassLoader classLoader = new GroovyClassLoader();
    private static final GroovyScriptEngineFactory scriptEngineFactory = new GroovyScriptEngineFactory();

    public static <T> T invoke(String script, String function, Object... objects) {
        try {
            ScriptEngine scriptEngine = scriptEngineFactory.getScriptEngine();
            scriptEngine.eval(script);
            return (T) ((Invocable) scriptEngine).invokeFunction(function, objects);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 转换内容
     *
     * @param content
     * @return
     */
    public static String transform(String content, Map<String, Object> data) {
        try {
            log.info("传入内容{} 数据:{}", content, JSONObject.toJSONString(data));
            if (content == null || StringUtils.isEmpty(content.trim())) {
                return null;
            }

            String str = content.replaceAll("\\\"", "\\\\\"").replaceAll("\n", "");
            StringBuilder sb = new StringBuilder();
            sb.append("String str=\"")
                    .append(str)
                    .append("\";").append("return str;");
            System.out.println(sb.toString());
            ScriptEngine scriptEngine = scriptEngineFactory.getScriptEngine();
            Bindings bindings = new SimpleBindings();
            if (data != null && !data.isEmpty()) {
                bindings.putAll(data);
            }
            String result = (String) scriptEngine.eval(sb.toString(), bindings);
            log.info("计算结果:{}", result);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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

    public static void main(String[] args) throws Exception {
//        try {
//           Map<String,Object> result= GroovyScriptUtil.invoke("  def Map test(){\n" +
//                    "        System.out.println(\"调用成功，传入的名字是\");\n" +
//                    "    Map<String,Object> result=new HashMap<String,Object>((int)(100/0.75));" +
//                   "        result.put(\"xxxx3\",\"xxxx3\");\n" +
//                    "   return result;" +
//                    "    }","test","xxx");
//           System.out.println(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        JSONObject json = new JSONObject();
        json.put("name", "${name}");
        Map<String, Object> data = new HashMap<>();
        data.put("name", "张三");
        System.out.println(GroovyScriptUtil.transform(json.toJSONString(), data));
    }

}
