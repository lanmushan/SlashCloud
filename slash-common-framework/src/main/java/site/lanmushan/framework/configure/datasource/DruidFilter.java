package site.lanmushan.framework.configure.datasource;

import lombok.extern.slf4j.Slf4j;
import site.lanmushan.framework.authorization.CurrentUserUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 避免掉druid页面拦截不了
 * @author Administrator
 */
@Slf4j
@WebFilter(urlPatterns="/*")
public class DruidFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request,response);
        return;
//        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse= (HttpServletResponse) response;
//       if( CurrentUserUtil.getCurrentUser().isAdmin()){
//           chain.doFilter(request,response);
//       }else {
//           httpServletResponse.sendRedirect("/");
//       }
     //   log.info("拦截成功");
    }

    @Override
    public void destroy() {

    }
}
