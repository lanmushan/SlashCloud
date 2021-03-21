package site.lanmushan.framework.configure.logid;


import org.jboss.logging.MDC;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/**
 * @author Administrator
 */
@Configuration
public class LogIdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestId = ((HttpServletRequest) request).getHeader("requestId");
        if (requestId == null) {
            requestId = "0";
        }
        MDC.put("requestId", requestId);
        chain.doFilter(request,response);
    }
}
