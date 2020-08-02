package site.lanmushan.framework.interceptor;

import com.alibaba.fastjson.JSONObject;
import site.lanmushan.framework.dto.QueryInfo;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 配置Feign携带header等相关请求信息
 *
 * @author Administrator
 */
@Component
@Slf4j
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //携带Header
        applyHeader(requestTemplate);
        customQueryParam(requestTemplate);

    }

    private void customQueryParam(RequestTemplate requestTemplate) {

        if ("GET".equals(requestTemplate.method())) {
            log.info("发送GET请求");
            try {
                //获取body内容
                String jsonStr = requestTemplate.requestBody().asString();
                if (StringUtils.isEmpty(jsonStr) || "Binary data".equals(jsonStr)) {
                    return;
                }

                JSONObject json = customQueryInfo(JSONObject.parseObject(jsonStr));
                if (json == null) {
                    return;
                }
                Iterator<Map.Entry<String, Object>> it = json.getInnerMap().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = it.next();
                    requestTemplate.query(entry.getKey().toString(), entry.getValue().toString());
                }
                Class requestClass = requestTemplate.getClass();
                Field field = requestClass.getDeclaredField("body");
                field.setAccessible(true);
                //修改body为空。
                field.set(requestTemplate, Request.Body.empty());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }


    }

    /**
     * 携带Header
     *
     * @param requestTemplate
     */
    private void applyHeader(RequestTemplate requestTemplate) {
        log.info("携带Header请求");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                requestTemplate.header(name, values);
            }
        }
    }

    private String createUriQueryParams(JSONObject json) {
        StringBuffer sb = new StringBuffer();
        json.forEach((key, value) -> {
            sb.append(key).append("+").append(value).append("&");
        });
        String str = sb.toString();
        if (str.endsWith("&")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * 参数为QueryInfo时进行特殊处理
     *
     * @param json
     */
    private JSONObject customQueryInfo(JSONObject json) {
        if (!json.containsKey(QueryInfo.infoMark.mark.key)) {
            return json;
        }
        String mark = json.getString(QueryInfo.infoMark.mark.key);
        if (!mark.equals(QueryInfo.infoMark.mark.value)) {
            return json;
        }
        json.remove("parList");
        json.remove("orders");
        json.remove(QueryInfo.infoMark.mark.key);
        JSONObject resultJson = new JSONObject();
        Iterator<Map.Entry<String, Object>> iterator = json.getInnerMap().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            if (null == entry.getValue() || "".equals(entry.getValue())) {
                continue;
            }
            if ("map".equals(entry.getKey())) {
                Map map = json.getJSONObject("map").getInnerMap();
                Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Object> row = it.next();
                    resultJson.put(row.getKey(), row.getValue());
                }
            } else {
                resultJson.put(entry.getKey(), entry.getValue());
            }


        }
        log.info("请求参数:{} ", resultJson.toJSONString());
        return resultJson;
    }

}
