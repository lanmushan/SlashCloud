package site.lanmushan.framework.configure.requestId;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 日志
 *
 * @Author dy
 * @Date 2020/6/11 19:35
 * @Version 1.0
 */
@Component
@Slf4j
public class RequestIDInterceptorMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private RequestIDInterceptor requestIDInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestIDInterceptor).addPathPatterns("/**");
        log.info("requestIDInterceptor拦截器设置 成功");
    }
}
