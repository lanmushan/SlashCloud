package site.lanmushan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author dy
 */
@ConditionalOnClass(ApplicationContextAware.class)
@Configuration
@Slf4j
public class ApplicationUtil implements ApplicationContextAware {
    private static ApplicationContext application = null;
    private static RedisTemplate<String,String> stringStringRedisTemplate;

    public static RedisTemplate<String, Object> getRedisTemplate() {
        return (RedisTemplate<String, Object>) application.getBean("redisTemplate");
    }
    public static RedisTemplate<String, String> getStringRedisTemplate(){
        return stringStringRedisTemplate;
    }
    @Resource
    public void setStringRedisTemplate(RedisTemplate<String,String> redisTemplate){
        stringStringRedisTemplate=redisTemplate;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (application == null) {
            application = applicationContext;
        }
        log.info("applicationContext加载成功");
    }


    public static void autowireBean(Object bean) {
        application.getAutowireCapableBeanFactory().autowireBean(bean);
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
