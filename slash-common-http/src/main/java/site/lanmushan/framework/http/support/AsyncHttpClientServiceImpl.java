package site.lanmushan.framework.http.support;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import site.lanmushan.framework.http.api.AsyncHttpClientCallBack;
import site.lanmushan.framework.http.api.AsyncHttpClientService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
public class AsyncHttpClientServiceImpl extends AbstractHttpClientService implements AsyncHttpClientService {

    private volatile CloseableHttpAsyncClient httpClient;

    public AsyncHttpClientServiceImpl(int poolSize, int maxPerRoute, int socketTimeout, int connectTimeout) {
        super(poolSize, maxPerRoute, socketTimeout, connectTimeout);
    }


    @Override
    public Object doPost(String url, Object data, Map<String, String> header, Class<?> returnClass) {
        HttpResponse response = doHttp(url, HTTP_POST, data, header);
        return parseObject(response, returnClass);
    }

    @Override
    public Object doGet(String url, Object data, Map<String, String> header, Class<?> returnClass) {
        HttpResponse response = doHttp(url, HTTP_GET, data, header);
        return parseObject(response, returnClass);
    }

    @Override
    public void doPost(String url, Object data, Map<String, String> header, AsyncHttpClientCallBack callBack) {
        try {
            doHttp(url, HTTP_POST, data, header,callBack);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(String url, Object data, Map<String, String> header, AsyncHttpClientCallBack callBack) {
        try {
            doHttp(url, HTTP_POST, data, header,callBack);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public HttpResponse doHttp(String url, String method, Object data, Map<String, String> header) {
        long startTime = System.currentTimeMillis();
        HttpRequestBase httpMethod = createHttpMethod(url, method, data, header);
        Future<HttpResponse> httpResponseFuture = getHttpClient().execute(httpMethod, null);
        try {
            return httpResponseFuture.get();
        } catch (InterruptedException e) {
            httpResponseFuture.cancel(true);
            log.error(e.getMessage(), e);
        } catch (ExecutionException e) {
            httpResponseFuture.cancel(true);
            log.error(e.getMessage(), e);
        } finally {
            long end = System.currentTimeMillis();
            log.info("{} {} 耗时{}毫秒结果", method, url, (end - startTime));
        }
        return null;
    }

    @Override
    public void doHttp(String url, String method, Object data, Map<String, String> header, AsyncHttpClientCallBack callBack) throws ExecutionException, InterruptedException {
        HttpRequestBase httpMethod = createHttpMethod(url, method, data, header);
       getHttpClient().execute(httpMethod, callBack);
    }

    @Override
    public Object doPostJson(String url, Object data, Map<String, String> header, Class<?> returnClass) {
        if (header == null) {
            header = new HashMap<>(4);
            header.put("Content-Type", "application/json");
        }
        return this.doPost(url, data, header, returnClass);
    }

    @Override
    public void close() {
        try {
            getHttpClient().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CloseableHttpAsyncClient getHttpClient() {
        if (httpClient == null) {
            synchronized (this) {
                if (httpClient == null) {
                    try {
                        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setIoThreadCount(1).build();
                        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
                        PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(ioReactor);
                        connectionManager.setMaxTotal(poolSize);
                        connectionManager.setDefaultMaxPerRoute(maxPerRoute);
                        // 设置HTTP请求的超时配置
                        RequestConfig config = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
                        // 根据配置创建异步的HTTP客户端
                        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setDefaultRequestConfig(config).setConnectionManager(connectionManager).build();
                        //运行客户端
                        httpclient.start();
                        this.httpClient = httpclient;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return httpClient;
    }

    public static void main(String[] args) {

    }
}
