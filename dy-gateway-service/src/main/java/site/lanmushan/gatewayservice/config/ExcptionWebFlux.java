package site.lanmushan.gatewayservice.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.Message;

import java.nio.charset.StandardCharsets;

/**
 * 未登录处理类
 */
@Slf4j
@Service
public class ExcptionWebFlux implements ServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange serverWebExchange, AuthenticationException e) {
        log.info("访问未授权资源");
        ServerHttpResponse serverHttpResponse= serverWebExchange.getResponse();
        Message msg=Message.getInstance();
        msg.setHttpCode(HTTPCode.D601);
        String result = JSONObject.toJSONString(msg);
        serverHttpResponse.setStatusCode(HttpStatus.OK);
        serverHttpResponse.getHeaders().set("Content-Type","application/json;charset=utf-8'");
        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        return serverHttpResponse.writeWith(Mono.just(buffer));
    }
}
