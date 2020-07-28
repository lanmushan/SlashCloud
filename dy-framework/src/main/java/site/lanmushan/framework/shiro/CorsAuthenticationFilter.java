package site.lanmushan.framework.shiro;

import com.alibaba.fastjson.JSON;
import site.lanmushan.framework.dto.Message;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 自定义拦截未登录请求过滤器
 */
public class CorsAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse)response;
        res.setHeader("Access-Control-Allow-Origin", "http://localhost");
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        Message message=new Message();
        message.success("未登录");
        message.setCode( 702);
        writer.write(JSON.toJSONString(message));
        writer.close();
        return false;
    }

}
