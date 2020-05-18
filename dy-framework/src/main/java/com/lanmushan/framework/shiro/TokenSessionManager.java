package com.lanmushan.framework.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;


/**
 * @author Administrator
 */
public class TokenSessionManager extends DefaultWebSessionManager {
    private static final String AUTHORIZATION = "authorization";
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //如果请求头中有 Authorization 则其值为sessionId
        if (!StringUtils.isEmpty(token)) {
           // id= AESUtil.decrypt(id);//进行解密返回sessionId
            return token;
        } else {
            //否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }

    }
}
