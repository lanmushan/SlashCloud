package site.lanmushan.framework.shiro;

import site.lanmushan.framework.util.CurrentUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Administrator
 */
@Slf4j
public class TokenSessionManager extends DefaultWebSessionManager {
    private static final String AUTHORIZATION = "authorization";
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //如果请求头中有 Authorization 则其值为sessionId
        Serializable sessionId = "";

        if (!StringUtils.isEmpty(token)) {
            sessionId = CurrentUserUtil.getSessionId(token);
            log.debug("根据Token获取SessionId|{}",sessionId);
        } else {
            //否则按默认规则从cookie取sessionId
            sessionId = super.getSessionId(request, response);
            log.debug("根据Cokie获取Session|{}",sessionId);
        }
        return sessionId;
    }

    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
