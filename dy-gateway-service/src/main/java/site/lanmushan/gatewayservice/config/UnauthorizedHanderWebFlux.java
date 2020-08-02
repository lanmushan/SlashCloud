package site.lanmushan.gatewayservice.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.AccessDeniedException;

import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UnauthorizedHanderWebFlux implements  ServerAccessDeniedHandler {


    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, AccessDeniedException e) {
        return null;
    }
}
