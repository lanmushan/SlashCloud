package site.lanmushan.framework.configure.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.lanmushan.framework.http.api.HttpClientService;
import site.lanmushan.framework.http.support.AsyncHttpClientServiceImpl;

@Configuration
public class HttpClientServiceConfig {
    @Bean
    HttpClientService createHttpClientService() {
        return new AsyncHttpClientServiceImpl(2, 6, 5000, 5000);
    }
}
