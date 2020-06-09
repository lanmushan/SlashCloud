package com.lanmushan.framework.configure.log4j;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

/**
 * @Author dy
 * @Date 2020/6/9 23:42
 * @Version 1.0
 */
//@Configuration
//@Order(Log4j2EnvConfig.DEFAULT_ORDER)
public class Log4j2EnvConfig implements GenericApplicationListener  {
    public static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 10;

    private static Class<?>[] EVENT_TYPES = {ApplicationStartingEvent.class,
            ApplicationEnvironmentPreparedEvent.class, ApplicationPreparedEvent.class,
            ContextClosedEvent.class, ApplicationFailedEvent.class};

    private static Class<?>[] SOURCE_TYPES = {SpringApplication.class,
            ApplicationContext.class};

    @Autowired
    private Environment env;

    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        String appName = env.getProperty("spring.application.name");
        if (StringUtils.isNotBlank(appName)) {
            MDC.put("appName", appName);
        }

        String port = env.getProperty("server.port");
        if (StringUtils.isNotBlank(port)) {
            MDC.put("port", port);
        }
    }
}
