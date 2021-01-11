package site.lanmushan.framework.util.utils;



import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class HttpClientUtils {
    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";

    public static String doPost(String url, Object obj, Map<String, String> header) {
        return doHttpString(url, HttpClientUtils.HTTP_POST, obj, header);
    }

    public static JSONObject doPostJson(String url, Object obj, Map<String, String> header) {
        header.put("Content-Type", "application/json");
        String result = doPost(url, obj, header);
        return (JSONObject) JSONObject.parse(result);
    }

    public static JSONObject doGetJson(String url, Object obj, Map<String, String> header) {
        String result = doGet(url, obj, header);
        return (JSONObject) JSONObject.parse(result);
    }

    public static String doGet(String url, Object obj, Map<String, String> header) {
        return doHttpString(url, HttpClientUtils.HTTP_GET, obj, header);
    }

    private static String doHttpString(String url, String method, Object data, Map<String, String> header) {
        HttpResponse response = HttpClientUtils.doHttp(url, method, data, header);
        try {
            String result= EntityUtils.toString(response.getEntity(), "utf-8");
            log.info("HTTP {} {} 结果:{}",method,url,result);
            return result;
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException("Http请求异常");
        }
    }

    private static HttpResponse post(String url, String data, Map<String, String> header) {
        return HttpClientUtils.doHttp(url, HttpClientUtils.HTTP_POST, data, header);
    }

    private static HttpResponse get(String url, String data, Map<String, String> header) {
        return HttpClientUtils.doHttp(url, HttpClientUtils.HTTP_GET, data, header);
    }

    public static File doDownLoad(String url, String filepath) {
        HttpResponse response = HttpClientUtils.doHttp(url, HttpClientUtils.HTTP_GET, null, null);
        HttpEntity entity = response.getEntity();
        InputStream is = null;
        try {
            is = entity.getContent();
            File file = new File(filepath);
            file.getParentFile().mkdirs();
            FileOutputStream fileout = new FileOutputStream(file);
            byte[] buffer = new byte[2048];
            int ch = 0;
            while ((ch = is.read(buffer)) != -1) {
                fileout.write(buffer, 0, ch);
            }
            is.close();
            fileout.flush();
            fileout.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static HttpResponse doHttp(String url, String method, Object data, Map<String, String> header) {
        try {
            HttpClient httpClient = HttpsClients.create();
            HttpRequestBase httpMethod = null;
            String fillData = getFillData(method, data, header);
            if (HTTP_GET.equalsIgnoreCase(method)) {
                if (null != fillData) {
                    url = url + "?" + fillData;
                }
                httpMethod = new HttpGet(url);
            } else {
                httpMethod = new HttpPost(url);
                if (null != fillData) {
                    StringEntity entity = new StringEntity(fillData, StandardCharsets.UTF_8);
                    ((HttpPost) httpMethod).setEntity(entity);
                }
            }
            log.info("请求地址:{}",url);
            return httpClient.execute(httpMethod);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getFillData(String method, Object data, Map<String, String> header) {
        String result = null;
        if (data == null) {
            return result;
        }
        if (data instanceof String) {
            return data.toString();
        }
        if (HttpClientUtils.HTTP_GET.equals(method)) {
            result = JsonUrlUtil.parseNameValue(data);
            return result;
        }
        if(header==null)
        {
            return null;
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

    public static void main(String[] args) throws IOException {

    }
}
