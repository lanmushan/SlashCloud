package site.lanmushan.framework.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.lanmushan.framework.file.LocalResourceUtils;

/**
 * @Author dy
 * @Date 2020/4/21 21:15
 * @Version 1.0
 * 外部资源文件配置
 */
@Configuration
@Slf4j
public class WebFileMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
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
            registry.addResourceHandler("/resource/public/**").addResourceLocations("file:"+ LocalResourceUtils.getLocalResourcePublicPath());
        }
        log.info("外部资源文件设置成功{}","file:"+ LocalResourceUtils.getLocalResourcePublicPath());
    }
}
