package site.lanmushan.gatewayservice.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import site.lanmushan.authorization.CurrentUser;
import site.lanmushan.authorization.CurrentUserUtil;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.json.JsonUtil;
import site.lanmushan.gatewayservice.util.ResponseUtil;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录过滤器
 *
 * @author dy
 */
@Slf4j
@Service
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
