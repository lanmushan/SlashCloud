package site.lanmushan.cms.web.view;

import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import site.lanmushan.framework.redis.RedisClientService;
import site.lanmushan.framework.util.ApplicationUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Locale;
import java.util.Map;

/**
 * @author Administrator
 */
public class FreeMarkerStaticView extends FreeMarkerView {
    public static String STATIC_URL_PREFIX = "STATIC_URL_PREFIX|";
    public static String PAGE_RE_WRITE_TIME = "PAGE_RE_WRITE_TIME";
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    protected void doRender(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Expose model to JSP tags (as request attributes).
        exposeModelAsRequestAttributes(model, request);
        // Expose all standard FreeMarker hash models.
        SimpleHash fmModel = buildTemplateModel(model, request, response);
        // Grab the locale-specific version of the template.
        Locale locale = RequestContextUtils.getLocale(request);
        createCache(getTemplate(locale), fmModel, request, response);
        processTemplate(getTemplate(locale), fmModel, response);
    }

    public void createCache(Template template, SimpleHash model, HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException, ServletException {
        // 站点根目录的绝对路径
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Writer out = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, "UTF-8"));
        // 处理模版
        template.process(model, out);
        out.flush();
        out.close();
        RedisClientService redisClientService = ApplicationUtil.getBean(RedisClientService.class);
        Integer staticTime = (Integer) request.getAttribute(PAGE_RE_WRITE_TIME);
        if (staticTime != null && staticTime != 0) {
            redisClientService.setex(STATIC_URL_PREFIX + request.getRequestURI(), staticTime, byteArrayOutputStream.toString());
        }
        /* 将请求转发到生成的htm文件 */
    }


}
