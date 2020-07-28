package com.lanmushan.framework.configure;

import com.lanmushan.framework.util.file.LocalResourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 跨域配置
 */
//@Configuration
@Slf4j
public class SecurityCorsConfiguration {
//
//    @SuppressWarnings("unchecked")
//    @Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost"));
//        corsConfiguration.setAllowedHeaders();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        log.info("跨域请求设置成功");
//        return bean;
//    }
}
