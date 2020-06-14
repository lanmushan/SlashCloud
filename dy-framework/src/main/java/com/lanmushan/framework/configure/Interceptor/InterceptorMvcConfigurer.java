package com.lanmushan.framework.configure.Interceptor;

import com.lanmushan.framework.interceptor.RequestIDInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 日志
 * @Author dy
 * @Date 2020/6/11 19:35
 * @Version 1.0
 */
@Component
@Slf4j
public class InterceptorMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private RequestIDInterceptor requestUIDInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestUIDInterceptor).addPathPatterns("/**");
        log.info("拦截器设置成功");
    }
}
