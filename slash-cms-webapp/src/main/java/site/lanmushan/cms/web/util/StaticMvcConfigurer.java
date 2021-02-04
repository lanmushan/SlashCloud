package site.lanmushan.cms.web.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Slf4j
//@Configuration
public class StaticMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    StaticHandlerInterceptor staticHandlerInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(staticHandlerInterceptor).addPathPatterns("/**");
      //  log.info("staticHandlerInterceptor拦截器设置 成功");
    }
}
