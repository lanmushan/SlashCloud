package site.lanmushan.framework.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.lanmushan.framework.file.LocalResourceService;
import site.lanmushan.framework.file.LocalResourceServiceImpl;

import java.io.IOException;

@Configuration
@Slf4j
public class LocalResourceConfig {
    @Value("${resourcePath:.\\resource}")
    private String resourcePath;
    @Bean
    public LocalResourceService localResourceService() {
        try {
            return new LocalResourceServiceImpl(resourcePath);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }
}
