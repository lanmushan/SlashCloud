package site.lanmushan.cms.web.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * @date 2017/4/23
 */
@Component
@Slf4j
public class StaticHandlerInterceptor implements HandlerInterceptor {

    //请求
    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) throws Exception {
        System.out.println("-------------请求开始:" + servletRequest.getRequestURI());
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//        String classId = (String)pathVariables.get("classId");
//        System.out.println("classId: " + classId);
//        if ("1234".equals(classId))
//        {
//            return true;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("访问域名:{}", request.getServerName() + request.getRequestURI());
        log.info("题啊黄钻路径" + "/test" + "/" + request.getRequestURI());
        request.getRequestDispatcher("test2.html").forward(request, response);
    }

    @Override

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("-------------请求结束:" + System.currentTimeMillis());
    }


}