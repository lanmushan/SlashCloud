package site.lanmushan.framework.query.util;

import com.alibaba.fastjson.JSON;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
public class ServletUtil {
    private static final String N255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    private static final Pattern PATTERN = Pattern.compile("^(?:" + N255 + "\\.){3}" + N255 + "$");
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
    /**
     * 获取request参数
     *
     * @param httpServletRequest
     * @return
     */
    public static Map<String, Object> getDataFromRequest(HttpServletRequest httpServletRequest) {
        String type = httpServletRequest.getContentType();
        Map<String, Object> receiveMap = new HashMap<String, Object>();
        if ("GET".equals(httpServletRequest.getMethod()) || type == null ||"multipart/form-data".equals(type)|| "application/x-www-form-urlencoded".equals(type)) {
            Enumeration<String> enu = httpServletRequest.getParameterNames();
            while (enu.hasMoreElements()) {
                String key = String.valueOf(enu.nextElement());
                String value = httpServletRequest.getParameter(key);
                receiveMap.put(key, value);
            }
        } else {    //else是text/plain、application/json这两种情况
            BufferedReader reader = null;
            StringBuilder sb = new StringBuilder();
            try {
                reader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream(), StandardCharsets.UTF_8));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != reader) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isEmpty(sb.toString())) {
                return receiveMap;
            }
            return JSON.parseObject(sb.toString(), HashMap.class);
        }
        return receiveMap;

    }
    private static String longToIpV4(long longIp) {
        int octet3 = (int) ((longIp >> 24) % 256);
        int octet2 = (int) ((longIp >> 16) % 256);
        int octet1 = (int) ((longIp >> 8) % 256);
        int octet0 = (int) ((longIp) % 256);
        return octet3 + "." + octet2 + "." + octet1 + "." + octet0;
    }

    private static long ipV4ToLong(String ip) {
        String[] octets = ip.split("\\.");
        return (Long.parseLong(octets[0]) << 24) + (Integer.parseInt(octets[1]) << 16)
                + (Integer.parseInt(octets[2]) << 8) + Integer.parseInt(octets[3]);
    }

    private static boolean isIPv4Private(String ip) {
        long longIp = ipV4ToLong(ip);
        return (longIp >= ipV4ToLong("10.0.0.0") && longIp <= ipV4ToLong("10.255.255.255"))
                || (longIp >= ipV4ToLong("172.16.0.0") && longIp <= ipV4ToLong("172.31.255.255"))
                || longIp >= ipV4ToLong("192.168.0.0") && longIp <= ipV4ToLong("192.168.255.255");
    }

    private static boolean isIPv4Valid(String ip) {
        return PATTERN.matcher(ip).matches();
    }

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    public static String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
}
