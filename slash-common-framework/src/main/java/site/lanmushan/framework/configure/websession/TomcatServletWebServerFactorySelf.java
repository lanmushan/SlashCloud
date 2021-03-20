package site.lanmushan.framework.configure.websession;

import org.apache.catalina.Context;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.stereotype.Component;

/**
 * 来源
 * https://blog.csdn.net/songxiuliang/article/details/108534779
 * @author
 */
@Component
public class TomcatServletWebServerFactorySelf extends TomcatServletWebServerFactory {
    @Override
    protected void postProcessContext(Context context) {
        context.setManager(new NoSessionManager());

    }
}