package site.lanmushan.gatewayservice.filter;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * @Author dy
 * @Date 2020/6/10 22:15
 * @Version 1.0
 */
@Component
public class RequestIDFilter implements GlobalFilter, Ordered {
    private static final String PREFIX="GATEWAY-";
     private Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //period time
        final String uuid = PREFIX+UUID.randomUUID().toString().replace("-","").toUpperCase();
        long startTime=System.currentTimeMillis();
        Consumer<HttpHeaders> httpHeaders = httpHeader -> {
            httpHeader.set("requestId", uuid);
        };
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers=super.getHeaders();
                long endTime=System.currentTimeMillis();
               // log.info("修改UID:{}",uuid);
                headers.set("requestId",uuid);
                headers.set("times",Long.toString(endTime-startTime));
                headers.setAccessControlAllowCredentials(true);
                headers.setAccessControlAllowOrigin("*");
                headers.setAccessControlMaxAge(3000);
                List<HttpMethod> methods= Arrays.asList(HttpMethod.GET,HttpMethod.POST,HttpMethod.OPTIONS);
                headers.setAccessControlAllowMethods(methods);
                return headers;
            }
//            @Override
//            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
//                if (body instanceof Flux) {
//                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
//                    return super.writeWith(fluxBody.map(dataBuffer -> {
//                        // probably should reuse buffers
//                        byte[] content = new byte[dataBuffer.readableByteCount()];
//                        dataBuffer.read(content);
//                        //释放掉内存
//                        DataBufferUtils.release(dataBuffer);
//                        String s = new String(content, Charset.forName("UTF-8"));
//                        //TODO，s就是response的值，想修改、查看就随意而为了
//                        byte[] uppedContent = new String(content, Charset.forName("UTF-8")).getBytes();
//                        System.out.println(s);
//                        return bufferFactory.wrap(uppedContent);
//                    }));
//                }
//                // if body is not a flux. never got there.
//                return super.writeWith(body);
//            }
        };
        exchange.mutate().request(serverHttpRequest).build();
        // replace response with decorator
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }



    @Override
    public int getOrder() {
        return 1;
    }
}
