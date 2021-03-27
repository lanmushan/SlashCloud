package site.lanmushan.cms.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import site.lanmushan.cms.web.view.FreeMarkerStaticView;
import site.lanmushan.framework.cypher.md5.MD5Util;
import site.lanmushan.framework.file.LocalResourceService;
import site.lanmushan.framework.util.OSUtil;
import site.lanmushan.framework.util.ServletUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 */
@Slf4j
@Component
public class StaticHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    public static String STATIC_URL_PREFIX = FreeMarkerStaticView.STATIC_URL_PREFIX;
    @Autowired
    LocalResourceService localResourceService;

    //请求
    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) throws IOException {
        Long startTime = System.currentTimeMillis();
        servletRequest.setAttribute("startTime", startTime);
        String uri = servletRequest.getRequestURI();
        String path = OSUtil.convertToCurrentOsPath(uri);
        String key = STATIC_URL_PREFIX + MD5Util.createMD532(servletRequest.getRequestURL().toString());
        Object resultStr = redisTemplate.opsForValue().get(key);
        //TODO 这里有缺陷，contentType，没有存储
        if (resultStr != null) {
            servletResponse.setContentType("text/html;charset=UTF-8");
            servletResponse.getOutputStream().write(((String) resultStr).getBytes());
            log.info("命中Redis静态化耗时 KEY:{}: 时耗{}", key, System.currentTimeMillis() - startTime);
            return false;
        }
        //磁盘静态化只处理html后缀的数据
        if (uri.endsWith(".html") && localResourceService.webExists(path)) {
            servletResponse.setContentType("text/html;charset=UTF-8");
            servletResponse.getOutputStream().write(localResourceService.webRead(path));
            log.info("命中磁盘静态化耗时 url:{}时耗:{}", servletRequest.getRequestURI(), System.currentTimeMillis() - startTime);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Long startTime = (Long) request.getAttribute("startTime");
        // log.info("控制器处理 url:{}时耗:{}",request.getRequestURI(),System.currentTimeMillis()-startTime);

    }

}
