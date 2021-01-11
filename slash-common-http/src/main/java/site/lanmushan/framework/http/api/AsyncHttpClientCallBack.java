package site.lanmushan.framework.http.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;
import site.lanmushan.framework.http.support.AbstractHttpClientService;
import site.lanmushan.framework.http.support.AsyncHttpClientServiceImpl;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
@Slf4j
public class AsyncHttpClientCallBack implements FutureCallback<HttpResponse> {
    private Consumer runnable;

    public AsyncHttpClientCallBack(Consumer runnable) {
        this.runnable = runnable;
    }

    @Override
    public void completed(HttpResponse httpResponse) {
        try {
            String result = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            runnable.accept(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Exception e) {
        log.error(e.getMessage(),e);
        runnable.accept(null);
    }

    @Override
    public void cancelled() {
        runnable.accept(null);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HttpClientService httpClientService = new AsyncHttpClientServiceImpl(2, 1, 2000, 200);
        long start = System.currentTimeMillis();
        AsyncHttpClientCallBack callBack = new AsyncHttpClientCallBack((x) -> {
            System.out.println("哈哈哈哈:+" + x.toString());
            System.out.println("请求耗时:{}" + (System.currentTimeMillis() - start));

        });

        httpClientService.doHttp("https://www.runoob.com/java/java8-functional-interfaces.html", AbstractHttpClientService.HTTP_GET, null, null, callBack);
        httpClientService.close();
    }
}
