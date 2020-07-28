package site.lanmushan.framework.util;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;

public class ServletUtil {
    /**
     * 获取登录浏览器
     *
     * @param request
     * @return
     */
    public static String getLoginBrowser(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        Version browserVersion = userAgent.getBrowserVersion();
        return browser.getName() + browserVersion.getVersion();
    }

    /**
     * 获取登录系统
     *
     * @param request
     * @return
     */
    public static String getLoginOs(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        return operatingSystem.getName();
    }
}
