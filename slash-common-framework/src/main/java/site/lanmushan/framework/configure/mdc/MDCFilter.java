package site.lanmushan.framework.configure.mdc;


import org.jboss.logging.MDC;
import org.springframework.context.annotation.Configuration;
import site.lanmushan.framework.authorization.CurrentUser;
import site.lanmushan.framework.authorization.CurrentUserUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Administrator
 */
@Configuration
public class MDCFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestId = ((HttpServletRequest) request).getHeader("requestId");
        if (requestId == null) {
            requestId = "0";
        }
        MDC.put("requestId", requestId);
        String token = ((HttpServletRequest) request).getHeader(CurrentUserUtil.AUTHORIZATION);
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser(token);
        if (currentUser != null) {
            MDC.put("account", currentUser.getAccount());
        } else {
            MDC.put("account", "anon");
        }
        chain.doFilter(request, response);
    }
}
