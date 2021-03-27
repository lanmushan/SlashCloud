package site.lanmushan.framework.authorization.filter;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.authorization.SignFilterManager;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.exception.OperateException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Administrator
 */
@Slf4j
public class SignAuthFilter implements Filter {
    @Autowired
    SignFilterManager signFilterManager;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String token = httpServletRequest.getHeader(CurrentUserUtil.AUTHORIZATION);
        String uri = httpServletRequest.getRequestURI();
        try {
            Boolean result = signFilterManager.allow(uri, token);
            if (result) {
                chain.doFilter(request, response);
                return;
            }
            throw new OperateException("无访问权限", HTTPCode.D601);
        } catch (OperateException e) {
            log.error("请求地址:{}",((HttpServletRequest) request).getRequestURL().toString());
            log.error(e.getMessage(),e);
            Message msg = Message.getInstance().error(e.getHttpCode(), e.getMessage());
            httpServletResponse.setStatus(HttpStatus.OK.value());
            httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
            httpServletResponse.getOutputStream().write(JSONObject.toJSONString(msg).getBytes(StandardCharsets.UTF_8));
        } catch (Throwable e) {
            log.error("请求地址:{}",((HttpServletRequest) request).getRequestURL().toString());
            log.error(e.getMessage(),e);
            Message msg = Message.getInstance().error(HTTPCode.S500, "服务器内部错误");
            httpServletResponse.setStatus(HttpStatus.OK.value());
            httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
            httpServletResponse.getOutputStream().write(JSONObject.toJSONString(msg).getBytes(StandardCharsets.UTF_8));
        }
    }

}
