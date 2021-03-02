package site.lanmushan.gateway.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.Message;

import java.nio.charset.StandardCharsets;

public class ResponseUtil {

    public static Mono<Void> doHandleReturn(ServerWebExchange exchange, HTTPCode code) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        Message msg = Message.getInstance();
        msg.setHttpCode(code);
        String result = JSONObject.toJSONString(msg);
        serverHttpResponse.setStatusCode(HttpStatus.OK);
        serverHttpResponse.getHeaders().set("Content-Type", "application/json;charset=utf-8");
        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        return serverHttpResponse.writeWith(Mono.just(buffer));
    }

}
