package site.lanmushan.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    UnauthorizedHanderWebFlux unauthorizedHanderWebFlux;
    @Autowired
    CustomReactiveAuthorizationManager customReactiveAuthorizationManager;
    @Autowired
    ExcptionWebFlux excptionWebFlux;

    //security的鉴权排除列表
    private static final String[] excludedAuthPages = {
            "/api/authLogin/selectVerificationCode",
            "/unLogin",
            "/health",
            "/api/socket/**"
    };

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                //自定义登录验证。自动扫描注入，无需手动注入
                //  .authenticationManager(loginReactiveAuthenticationManager)
                .authorizeExchange()
                //无需进行权限过滤的请求路径
                .pathMatchers(excludedAuthPages).permitAll()
                //option 请求默认放行
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .and()
                //自定义的鉴权服务，通过鉴权的才能继续访问某个请求
                .authorizeExchange().pathMatchers("/**").access(customReactiveAuthorizationManager)
                .anyExchange().authenticated()
                .and().addFilterAt(new LoginWebFilter(), SecurityWebFiltersOrder.FIRST)
                //  .addFilter(new JwtAuthenticationFilter(authenticationManager()));

               .httpBasic()
                .and()
                .formLogin()
                //指定登录请求路径
                .loginPage("/unLogin")
                //认证成功
               // .authenticationSuccessHandler(loginSuccessHandlerWebFlux)
                //登陆验证失败
              //  .authenticationFailureHandler(loginFailedHandlerWebFlux)
                .and().csrf().disable()//必须支持跨域
                .logout().logoutUrl("/auth/logout");

        //添加自定义异常入口，处理accessdeine异常
        http.exceptionHandling().authenticationEntryPoint(excptionWebFlux)
                .accessDeniedHandler(unauthorizedHanderWebFlux);


        return http.build();
    }
}
