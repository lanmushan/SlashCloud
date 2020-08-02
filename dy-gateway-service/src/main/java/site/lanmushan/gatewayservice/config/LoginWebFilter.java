package site.lanmushan.gatewayservice.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
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

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class LoginWebFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        List<String> headers = exchange.getRequest().getHeaders().get(CurrentUserUtil.AUTHORIZATION);

        if (null == headers || headers.size() > 1) {
            return doUnLogin(exchange);
        }
        String token = headers.get(0);
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser(token);
        if (currentUser == null) {
            return doUnLogin(exchange);
        }
        exchange.getRequest().mutate().header("currentUser",CurrentUserUtil.toBase64(currentUser));
        return chain.filter(exchange);
    }
    private Mono<Void> doUnLogin(ServerWebExchange exchange) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        Message msg = Message.getInstance();
        msg.setHttpCode(HTTPCode.D600);
        String result = JSONObject.toJSONString(msg);
        serverHttpResponse.setStatusCode(HttpStatus.OK);
        serverHttpResponse.getHeaders().set("Content-Type", "application/json;charset=utf-8'");
        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        return serverHttpResponse.writeWith(Mono.just(buffer));
    }
}
