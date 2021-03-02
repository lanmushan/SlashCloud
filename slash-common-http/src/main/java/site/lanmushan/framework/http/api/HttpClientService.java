package site.lanmushan.framework.http.api;

import org.apache.http.HttpResponse;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface HttpClientService {

    public Object doPost(String url, Object data, Map<String, String> header, Class<?> returnClass);

    public Object doGet(String url, Object data, Map<String, String> header, Class<?> returnClass);

    public void doPost(String url, Object data, Map<String, String> header, AsyncHttpClientCallBack callBack);

    public void doGet(String url, Object data, Map<String, String> header, AsyncHttpClientCallBack callBack);

    public HttpResponse doHttp(String url, String method, Object data, Map<String, String> header) throws ExecutionException, InterruptedException;

    public void doHttp(String url, String method, Object data, Map<String, String> header, AsyncHttpClientCallBack callBack) throws ExecutionException, InterruptedException;

    public Object doPostJson(String url, Object data, Map<String, String> header, Class<?> returnClass);

    public void close();
}
