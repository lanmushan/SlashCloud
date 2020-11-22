package site.lanmushan.gatewayservice.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import site.lanmushan.authorization.CurrentUser;
import site.lanmushan.authorization.CurrentUserUtil;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.gatewayservice.util.ResponseUtil;

import java.net.URI;
import java.util.List;

/**
 * 权限过滤器
 */
@Slf4j
@Order(value = 3)
//@Service
public class AuthorizationFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI uri = exchange.getRequest().getURI();
        log.info("权限校验 URL:{}", uri);
        List<String> headers = exchange.getRequest().getHeaders().get(CurrentUserUtil.AUTHORIZATION);
        String token = headers.get(0);
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser(token);
        if (currentUser == null) {
            log.error("请求token无效");
            return ResponseUtil.doHandleReturn(exchange, HTTPCode.D601);
        }
        if (currentUser.isAdmin()) {
            return chain.filter(exchange);
        }
        List<String> userApisList = CurrentUserUtil.getUserApis(currentUser);
        if (!userApisList.contains(uri.getPath())) {
            log.error("无访问权限");
            return ResponseUtil.doHandleReturn(exchange, HTTPCode.D601);
        }
        return chain.filter(exchange);
    }


}
