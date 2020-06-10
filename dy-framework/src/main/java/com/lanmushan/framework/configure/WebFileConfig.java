package com.lanmushan.framework.configure;

import com.lanmushan.framework.util.file.LocalResourceUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

/**
 * @Author dy
 * @Date 2020/4/21 21:15
 * @Version 1.0
 * 外部资源文件配置
 */
@Configuration
public class WebFileConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        String os = System.getProperty("os.name");
        if("win".equals(os.toLowerCase()))
        {
            registry.addResourceHandler("/resource/public/**").addResourceLocations("file:"+ LocalResourceUtils.getLocalResourcePublicPath());
        }else {
            registry.addResourceHandler("/resource/public/**").addResourceLocations("file:"+LocalResourceUtils.getLocalResourcePublicPath());
        }
    }
}
