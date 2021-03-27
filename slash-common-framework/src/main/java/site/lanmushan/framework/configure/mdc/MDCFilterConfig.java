package site.lanmushan.framework.configure.mdc;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author Administrator
 */
@Configuration
public class MDCFilterConfig {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public FilterRegistrationBean druidPageFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new MDCFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        //添加不需要忽略的格式信息.
        return filterRegistrationBean;
    }
}
