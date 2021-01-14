package site.lanmushan.framework.query.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author dy
 */
@Component
@Slf4j
public class ApplicationUtil implements ApplicationContextAware {
    private static ApplicationContext application = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (application == null) {
            application = applicationContext;
        }
        log.info("applicationContext加载成功");
    }

    public static ApplicationContext getApplication() {
        return application;
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplication().getBean(clazz);
    }

    public static <T> T getBean(String clazz) {
        return (T) getApplication().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return (T) getApplication().getBean(clazz, clazz);
    }
}
