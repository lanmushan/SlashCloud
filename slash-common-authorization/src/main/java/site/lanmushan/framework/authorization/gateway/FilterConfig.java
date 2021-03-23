package site.lanmushan.framework.authorization.gateway;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import site.lanmushan.framework.authorization.SignFilterManager;
import site.lanmushan.framework.constant.annotation.ConditionalCloud;
import site.lanmushan.framework.util.json.JsonUtil;

import java.util.List;

/**
 * @author Administrator
 */
@Configuration
@ConditionalOnClass(GlobalFilter.class)
@Import(SignAuthGatewayFilter.class)
@ConditionalCloud
public class FilterConfig {
    @Autowired
    SignAuthGatewayFilter signAuthGatewayFilter;
 
    @Bean
    public GlobalFilter tokenFilter() {
        return signAuthGatewayFilter;
    }


}
