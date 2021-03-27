package site.lanmushan.framework.authorization.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import site.lanmushan.framework.authorization.SignFilterManager;
import site.lanmushan.framework.constant.annotation.ConditionalNotCloud;

/**
 * @author Administrator
 */
@Configuration
@Slf4j
@Import({SignAuthFilter.class, SignFilterManager.class})
@ConditionalNotCloud
@ConditionalOnProperty(value = "slash.auth",havingValue = "true",matchIfMissing = true)
public class SignAuthFilterConfig {
    @Autowired
    SignAuthFilter signAuthFilter;

    @Bean
    public FilterRegistrationBean druidPageFilter() {
        log.info("普通权限过滤器加载成功");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(signAuthFilter);
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE+1);
        //添加不需要忽略的格式信息.
        return filterRegistrationBean;
    }
}
