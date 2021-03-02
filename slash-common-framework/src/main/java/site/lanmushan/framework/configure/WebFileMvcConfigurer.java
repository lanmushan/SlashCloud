package site.lanmushan.framework.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.lanmushan.framework.file.LocalResourceService;

import java.io.File;

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
        configurer.setUseSuffixPatternMatch(false);
        configurer.setUseTrailingSlashMatch(true);
        configurer.setUseRegisteredSuffixPatternMatch(true);

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //这里使用最高优先级
        //  registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        String localResourcePath = "file:" + localResourceService.getWebAppPath() + File.separator;
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/**")
                .addResourceLocations(localResourcePath).addResourceLocations(localResourcePath + "cache" + File.separator);

        log.info("外部资源文件路径{}", localResourcePath);

    }
}
