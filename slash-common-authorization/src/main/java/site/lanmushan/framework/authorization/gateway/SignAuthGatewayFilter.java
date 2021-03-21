package site.lanmushan.framework.authorization.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.authorization.SignFilterManager;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.exception.OperateException;


/**
 * @author Administrator
 */
@Slf4j
public class SignAuthGatewayFilter implements GlobalFilter, Ordered {
    @Autowired
    SignFilterManager signFilterManager;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("GateWay鉴权开始............");
        String uri = exchange.getRequest().getURI().getPath();
        String token = exchange.getRequest().getHeaders().getFirst(CurrentUserUtil.AUTHORIZATION);
        try {
            Boolean result = signFilterManager.allow(uri, token);
            if (result) {
                return chain.filter(exchange);
            }
        } catch (OperateException e) {
           return ResponseUtil.doHandleReturn(exchange, e.getHttpCode());
        }
        return ResponseUtil.doHandleReturn(exchange, HTTPCode.D601);

    }



}
