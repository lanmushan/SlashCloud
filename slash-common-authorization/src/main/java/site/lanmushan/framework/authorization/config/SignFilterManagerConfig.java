package site.lanmushan.framework.authorization.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.lanmushan.framework.authorization.Authority;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.authorization.SignFilterManager;
import site.lanmushan.framework.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 */
@Configuration
@ConditionalOnProperty(value = "slash.auth",havingValue = "true",matchIfMissing = false)
public class SignFilterManagerConfig {

    @Bean
    public SignFilterManager signFilterManager() {
        JSONObject json = JsonUtil.loadJsonByClassPath("security.json");
        List<String> allowUrlList = json.getJSONArray("allows").toJavaList(String.class);
        List<Authority> authorityList = new ArrayList<>();
        allowUrlList.forEach(it -> {
            Authority authority =new Authority();
            authority.setOrder(1);
            authority.setRoleCodes(Arrays.asList(CurrentUserUtil.ANON_CODE));
            authority.setUrl(it);
            authorityList.add(authority);
        });

        return new SignFilterManager(authorityList);
    }
}
