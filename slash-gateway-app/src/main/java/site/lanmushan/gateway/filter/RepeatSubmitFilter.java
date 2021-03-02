package site.lanmushan.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 重复提交拦截器
 * 拦截所有的POST请求,将请求body数据通过md5散列存储到redis中进行判断拦截
 *
 * @author dy
 */
@Slf4j
@Order(value = 4)
//@Service
public class RepeatSubmitFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return null;
    }
}
