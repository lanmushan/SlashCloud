package site.lanmushan.cms.web.filter;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
//@Component
public class UrlFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper((HttpServletResponse) response);
        System.out.println(httpRequest.getRequestURI());
        String path=httpRequest.getRequestURI();
        request.getRequestDispatcher("/test/test.html").forward(request, response);
        return;
     //   chain.doFilter(request,response);
    }
}