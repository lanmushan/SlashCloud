package site.lanmushan.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.reactivestreams.Publisher;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * 微服务链路跟踪，这个过滤器中生成一个唯一的UUID，贯穿整个调用链
 *
 * @Author dy
 * @Date 2020/6/10 22:15
 * @Version 1.0
 */
//@Configuration
@Slf4j
@Order(value = 1)
public class RequestIDFilter implements GlobalFilter {
    private static final String PREFIX = "GW-";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //period time
        final String requestId = PREFIX + UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String url = exchange.getRequest().getURI().getPath();
        String method = exchange.getRequest().getMethodValue();
        log.info("Method:{} URL:{} RequestId:{}", method, url, requestId);
        MDC.put("requestId", requestId);
        long startTime = System.currentTimeMillis();
        Consumer<HttpHeaders> httpHeaders = httpHeader -> {
            httpHeader.set("requestId", requestId);
        };
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = super.getHeaders();
                long endTime = System.currentTimeMillis();
                headers.set("requestId", requestId);
                headers.set("times", Long.toString(endTime - startTime));
                return headers;
            }

            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
    /*            if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        // probably should reuse buffers
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        //释放掉内存
                        DataBufferUtils.release(dataBuffer);
                        String s = new String(content, Charset.forName("UTF-8"));
                        //TODO，s就是response的值，想修改、查看就随意而为了
                        byte[] uppedContent = new String(content, Charset.forName("UTF-8")).getBytes();
                        System.out.println(s);
                        return bufferFactory.wrap(uppedContent);
                    }));
                }*/
                // if body is not a flux. never got there.
                return super.writeWith(body);
            }
        };
        exchange.mutate().request(serverHttpRequest).build();
        // replace response with decorator
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }
}
