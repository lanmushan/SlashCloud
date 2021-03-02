package site.lanmushan.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import site.lanmushan.framework.authorization.CurrentUser;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.util.json.JsonUtil;
import site.lanmushan.gateway.util.ResponseUtil;

import java.util.List;

/**
 * 登录过滤器
 *
 * @author dy
 */
@Slf4j
//@Service
@Order(value = 2)
public class LoginWebFilter implements GlobalFilter {

    public static List<String> allowUrl;

    static {
        JSONObject json = JsonUtil.loadJsonByClassPath("security.json");
        allowUrl = json.getJSONArray("allows").toJavaList(String.class);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        List<String> headers = exchange.getRequest().getHeaders().get(CurrentUserUtil.AUTHORIZATION);
        //TODO 待优化
        if (allowUrl.contains(url)) {
            return chain.filter(exchange);
        }
        if (null == headers || headers.size() > 1) {
            log.error("请求未携带token");
            return ResponseUtil.doHandleReturn(exchange, HTTPCode.D600);
        }
        String token = headers.get(0);
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser(token);
        if (currentUser == null) {
            return ResponseUtil.doHandleReturn(exchange, HTTPCode.D600);
        }
        return chain.filter(exchange);
    }


}
