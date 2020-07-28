package site.lanmushan.framework.shiro;

import com.alibaba.fastjson.JSON;
import site.lanmushan.framework.dto.Message;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UrlFilter extends PermissionsAuthorizationFilter {
    /**
     * mappedValue 访问该url时需要的权限
     * subject.isPermitted 判断访问的用户是否拥有mappedValue权限
     * 重写拦截器，只要符合配置的一个权限，即可通过
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        System.out.println("URL拦截");
        Subject subject = getSubject(request, response);
        String url=((HttpServletRequest) request).getRequestURI();
        //return true;
        if(subject.isPermitted(url)){
            return true;
        }else {
            return false;
        }

    }

    /**
     * 未授权拦截
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse res = (HttpServletResponse)response;
        res.setHeader( "authorization",res.getHeader("authorization"));
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        Message message=new Message();
        message.success("没有权限访问");
        message.setCode(403);
        writer.write(JSON.toJSONString(message));
        writer.close();
        return false;
    }
}
