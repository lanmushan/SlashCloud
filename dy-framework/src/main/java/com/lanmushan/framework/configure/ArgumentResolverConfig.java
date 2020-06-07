package com.lanmushan.framework.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 自定义参数解析配置
 * @Author dy
 * @Date 2020/6/7 14:06
 * @Version 1.0
 */
@Configuration
public class ArgumentResolverConfig  implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(0,new RequestQueryInfoHandlerMethodArgumentResolver());
    }
}
