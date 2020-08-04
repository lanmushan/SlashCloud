package site.lanmushan.gatewayservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManagerAdapter;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import site.lanmushan.authorization.CurrentUser;
import site.lanmushan.authorization.CurrentUserUtil;

import java.net.URI;
import java.util.List;

/**
 * @author Administrator
 */
@Service
@Slf4j
public class CustomReactiveAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {


    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        URI uri = authorizationContext.getExchange().getRequest().getURI();

        if(LoginWebFilter.allowUrl.contains(uri.getPath()))
        {
            return Mono.just(new AuthorizationDecision(true));
        }
        log.info("权限校验");
        List<String> headers = authorizationContext.getExchange().getRequest().getHeaders().get(CurrentUserUtil.AUTHORIZATION);
        if (null == headers || headers.size() > 1) {
            return Mono.just(new AuthorizationDecision(false));
        }

        String token = headers.get(0);
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser(token);
        if (currentUser == null) {
            return Mono.just(new AuthorizationDecision(false));
        }
        if (CurrentUserUtil.isAdmin(currentUser)) {
            return Mono.just(new AuthorizationDecision(true));
        }
        List<String> userApisList = CurrentUserUtil.getCurrentUserApis(currentUser);




        if(userApisList.contains(uri.getPath()))
        {
            return Mono.just(new AuthorizationDecision(true));
        }
        return Mono.just(new AuthorizationDecision(false));
    }

}
