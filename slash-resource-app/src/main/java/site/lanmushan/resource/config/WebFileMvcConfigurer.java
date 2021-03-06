package site.lanmushan.resource.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
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
public class WebFileMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    LocalResourceService localResourceService;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //这里使用最高优先级
        //  registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        String localResourcePath = "file:" + localResourceService.getWebAppPath() + File.separator;
        registry.addResourceHandler("/**")
                .addResourceLocations(localResourcePath);
        log.info("外部静态资源文件路径{}", localResourcePath);

    }
}
