package site.lanmushan.cms.web.config;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import site.lanmushan.cms.web.view.FreeMarkerStaticView;
import site.lanmushan.framework.file.LocalResourceService;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Administrator
 */
@Configuration
@Slf4j
public class FreeMarkerConfig {
    @Autowired
    private LocalResourceService localResourceService;

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
        resolver.setViewNames("*ftl");
        resolver.setViewClass(FreeMarkerStaticView.class);
        resolver.setRequestContextAttribute("request");
        resolver.setExposeSpringMacroHelpers(true);
        resolver.setExposeRequestAttributes(true);
        resolver.setExposeSessionAttributes(true);
        resolver.setPrefix("/");
        resolver.setSuffix("");
        resolver.setOrder(2);
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
        String tplPath = "file:" + localResourceService.getTemplatesPath();
        log.info("FreeMarker模板路径:{}", tplPath);
        factory.setTemplateLoaderPaths(tplPath);
        factory.setDefaultEncoding("UTF-8");
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        freemarker.template.Configuration configuration = factory.createConfiguration();
        configuration.setClassicCompatible(true);
        result.setConfiguration(configuration);
        Properties settings = new Properties();
        settings.put("template_update_delay", "0");
        settings.put("default_encoding", "UTF-8");
        settings.put("number_format", "#");
        settings.put("datetime_format", "yyyy-MM-dd HH:mm:ss");
        settings.put("classic_compatible", true);
        settings.put("template_exception_handler", "ignore");
        settings.put("requestContextAttribute", "request");
        result.setFreemarkerSettings(settings);
        return result;
    }

}
