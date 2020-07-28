package site.lanmushan.framework.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 自定义SpringMVC参数解析器
 * @Author dy
 * @Date 2020/6/7 14:06
 * @Version 1.0
 */
@Configuration
@Slf4j
public class ArgumentResolverConfig  implements WebMvcConfigurer {
    @Autowired
    RequestQueryInfoHandlerMethodArgumentResolver requestQueryInfoHandlerMethodArgumentResolver;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(0,requestQueryInfoHandlerMethodArgumentResolver);
        log.info("@RequestQueryInfo参数解析设置成功");
    }
}
