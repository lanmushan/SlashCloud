package site.lanmushan.framework.configure.logid;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author Administrator
 */
@Configuration
public class LogIdFilterConfig {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public FilterRegistrationBean druidPageFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new LogIdFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("*");
        //添加不需要忽略的格式信息.
        return filterRegistrationBean;
    }
}
