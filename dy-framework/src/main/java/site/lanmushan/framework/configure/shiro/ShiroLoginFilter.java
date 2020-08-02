package site.lanmushan.framework.configure.shiro;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.Message;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import site.lanmushan.framework.util.CurrentUserUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 自定义拦截未登录请求过滤器
 */
@Slf4j
public class ShiroLoginFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
         return    super.onAccessDenied(request,response);

    }

}
