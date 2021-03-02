package site.lanmushan.framework.http.support;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import site.lanmushan.framework.http.api.HttpClientService;
import site.lanmushan.framework.http.util.JsonUrlUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 *
 */
@Slf4j
public abstract class AbstractHttpClientService implements HttpClientService {
    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";

    protected String proxy_host = "";
    protected int proxy_port = 0;
    protected String proxy_username = "";
    protected String proxy_password = "";


    protected int poolSize = 3000;// 连接池最大连接数
    protected int maxPerRoute = 1500;// 每个主机的并发最多只有1500
    protected int socketTimeout = 1000;// 设置等待数据超时时间5秒钟 根据业务调整
    protected int connectTimeout = 2000;// 连接超时
    protected boolean isProxy = false;

    public AbstractHttpClientService(int poolSize, int maxPerRoute, int socketTimeout, int connectTimeout) {
        this.poolSize = poolSize;
        this.maxPerRoute = maxPerRoute;
        this.socketTimeout = socketTimeout;
        this.connectTimeout = connectTimeout;
    }

    private static Object getFillData(String method, Object data, Map<String, String> header) {
        String result = null;
        if (data == null) {
            return result;
        }
        if (HTTP_GET.equals(method)) {
            result = JsonUrlUtil.parseNameValue(data);
            return result;
        }
        if (data instanceof String) {
            return data.toString();
        }
        if (header == null) {
            return data.toString();
        }
        String contentTypes = header.get("Content-Type");
        String contentType = contentTypes.split(";")[0];
        if ("multipart/form-data".equals(contentType) || "application/x-www-form-urlencoded".equals("")) {
            result = JsonUrlUtil.parseNameValue(data);
        } else {
            JSONObject json = (JSONObject) JSONObject.toJSON(data);
            result = json.toJSONString();
        }

        return result;

    }

    protected HttpRequestBase createHttpMethod(String url, String method, Object data, Map<String, String> header) {
        HttpRequestBase httpMethod = null;
        if ("GET".equalsIgnoreCase(method)) {
            httpMethod = new HttpGet(url + "？" + data);
        } else {
            HttpPost post = new HttpPost(url);
            if (header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    String mapKey = entry.getKey();
                    String mapValue = entry.getValue();
                    post.addHeader(mapKey, mapValue);
                }
            }
            Object inputData = getFillData(url, data, header);
            HttpEntity entity = null;
            if (inputData instanceof String) {
                entity = new StringEntity((String) inputData, StandardCharsets.UTF_8);
            }
            post.setEntity(entity);
            httpMethod = post;
        }
        return httpMethod;
    }

    protected Object parseObject(HttpResponse response, Class returnClass) {
        try {
            if (response == null) {
                return null;
            }
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
//            log.info("请求结果{}",result);
            if (returnClass == null || String.class.equals(returnClass)) {
                return result;
            } else {
                Object parse = JSONObject.parseObject(result, returnClass);
                return parse;
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
