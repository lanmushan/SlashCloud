package site.lanmushan.cms.web.datasource.handler;


import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import site.lanmushan.cms.web.datasource.DataSourceHandler;
import site.lanmushan.groovyscript.GroovyScriptUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Administrator
 */
@Service(value = "GroovyScriptDataHandler")
@Slf4j
public class GroovyScriptDataHandler implements DataSourceHandler {
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public Object doHandle(String dataSourceName, String content, Map<String, Object> params) {
        Object obj = GroovyScriptUtil.newInstance(content);
        try {
            if (obj == null || content == null) {
                return null;
            }
            Method method = obj.getClass().getMethod("doHandle", String.class, String.class, Map.class);
            applicationContext.getAutowireCapableBeanFactory().autowireBean(obj);
            Object result = method.invoke(obj, dataSourceName, content, params);
            return result;
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
            log.info("调用{} 内容{} 参数{}", dataSourceName, content, params);


        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
            log.info("调用{} 内容{} 参数{}", dataSourceName, content, params);

        } catch (InvocationTargetException e) {
            log.error(e.getMessage(), e);
            log.info("调用{} 内容{} 参数{}", dataSourceName, content, params);

        }
        return null;
    }
}
