package site.lanmushan.framework.authorization.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.lanmushan.framework.authorization.SignFilterManager;
import site.lanmushan.framework.util.json.JsonUtil;

import java.util.List;

/**
 * @author Administrator
 */
@Configuration
public class SignFilterManagerConfig {
    public static final List<String> allowUrlList;

    static {
        JSONObject json = JsonUtil.loadJsonByClassPath("security.json");
        allowUrlList = json.getJSONArray("allows").toJavaList(String.class);
    }

    @Bean
    public SignFilterManager signFilterManager() {
        return new SignFilterManager(allowUrlList);
    }
}
