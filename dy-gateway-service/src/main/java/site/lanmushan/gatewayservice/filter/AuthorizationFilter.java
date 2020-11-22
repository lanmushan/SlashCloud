package site.lanmushan.gatewayservice.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import site.lanmushan.authorization.CurrentUser;
import site.lanmushan.authorization.CurrentUserUtil;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.gatewayservice.util.ResponseUtil;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Order(value = 3)
public class CustomAuthorizationFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI uri = exchange.getRequest().getURI();
        log.info("权限校验 URL:{}", uri);
        List<String> headers = exchange.getRequest().getHeaders().get(CurrentUserUtil.AUTHORIZATION);
        if (null == headers || headers.size() > 1) {
            return ResponseUtil.doHandleReturn(exchange,HTTPCode.D601);
        }
        String token = headers.get(0);
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser(token);
        if (currentUser == null) {
            return ResponseUtil.doHandleReturn(exchange,HTTPCode.D601);
        }
        if (CurrentUserUtil.isAdmin(currentUser)) {
            return chain.filter(exchange);
        }
        List<String> userApisList = CurrentUserUtil.getCurrentUserApis(currentUser);
        if (userApisList.contains(uri.getPath())) {
            return ResponseUtil.doHandleReturn(exchange,HTTPCode.D601);
        }
        return chain.filter(exchange);
    }



}
