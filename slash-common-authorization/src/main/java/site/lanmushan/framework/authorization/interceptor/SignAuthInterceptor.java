package site.lanmushan.framework.authorization.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import site.lanmushan.framework.authorization.CurrentUser;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.exception.OperateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Administrator
 */
@Configuration
@Slf4j
public class SignAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.info("请求URI {}", uri);
        if (SignAuthInterceptorWebMvcConfigurer.allowUrlList.contains(uri)) {
            return true;
        }
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser();
        if (currentUser.isAdmin()) {
            return true;
        }
        List<String> userApisList = CurrentUserUtil.getUserApis(currentUser);
        if (!userApisList.contains(uri)) {
            throw new OperateException("无访问权限", HTTPCode.D601);
        }
        /**
         * 通过HttpServletRequest和HttpServletResponse做相应数据处理
         */
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
