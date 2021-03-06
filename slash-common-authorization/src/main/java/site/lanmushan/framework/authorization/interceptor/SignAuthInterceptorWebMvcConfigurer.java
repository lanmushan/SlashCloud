package site.lanmushan.framework.authorization.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;
import site.lanmushan.framework.util.json.JsonUtil;

import java.util.List;

/**
 * @author Administrator
 */
//@Configuration
//@ConditionalOnProperty(name = "slash.auth", havingValue = "true")
//@ConditionalOnWebApplication
//@ConditionalOnClass(HandlerInterceptor.class)
//@Import({RequestIdInterceptor.class,SignAuthInterceptor.class})
@Order(-999)
@Slf4j
public class SignAuthInterceptorWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    RequestIdInterceptor requestIdInterceptor;
     @Autowired
    SignAuthInterceptor signAuthInterceptor;
    public final static List<String> allowUrlList;

    static {
        JSONObject json = JsonUtil.loadJsonByClassPath("security.json");
        allowUrlList = json.getJSONArray("allows").toJavaList(String.class);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestIdInterceptor).addPathPatterns("/**");
        InterceptorRegistration registration= registry.addInterceptor(signAuthInterceptor);
        allowUrlList.forEach(it->{
            log.info("加载匿名访问接口:{}",it);
            registration.excludePathPatterns(it);
        });
    }

}
