package site.lanmushan.framework.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.lanmushan.framework.file.LocalResourceService;

/**
 * @Author dy
 * @Date 2020/4/21 21:15
 * @Version 1.0
 * 外部资源文件配置
 */
@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "slash", name = "cloud", havingValue = "false")
public class WebFileMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    LocalResourceService localResourceService;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String localResourcePath = "file:" + localResourceService.getWebAppPath() + "/";
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/**")
                .addResourceLocations(localResourcePath);
        log.info("外部资源文件路径{}", localResourcePath);

    }
}
