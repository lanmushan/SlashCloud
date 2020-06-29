package com.lanmushan.framework.configure;


import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Client;
//import feign.okhttp.OkHttpClient;
//import okhttp3.ConnectionPool;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
@ConditionalOnClass({OkHttpClient.class})
@ConditionalOnProperty("feign.okhttp.enabled")
@Slf4j
public class OkHttpFeignConfiguration {
    @Bean
    public Decoder decoder() {

        MappingJackson2HttpMessageConverter jacksonConverter = new MyFeignConverter(new ObjectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> {
            return new HttpMessageConverters(jacksonConverter);
        };
        return new SpringDecoder(objectFactory);
    }

    @Autowired
    CachingSpringLoadBalancerFactory cachingSpringLoadBalancerFactory;

    //
//    protected OkHttpFeignConfiguration() {
//    }
//
//   // @Bean
//    public ConnectionPool httpClientConnectionPool(FeignHttpClientProperties httpClientProperties, OkHttpClientConnectionPoolFactory connectionPoolFactory) {
//        Integer maxTotalConnections = httpClientProperties.getMaxConnections();
//        Long timeToLive = httpClientProperties.getTimeToLive();
//        TimeUnit ttlUnit = httpClientProperties.getTimeToLiveUnit();
//        return connectionPoolFactory.create(maxTotalConnections, timeToLive, ttlUnit);
//    }
//
//   // @Bean
//    public okhttp3.OkHttpClient client(OkHttpClientFactory httpClientFactory, ConnectionPool connectionPool, FeignHttpClientProperties httpClientProperties) {
//        Boolean followRedirects = httpClientProperties.isFollowRedirects();
//        Integer connectTimeout = httpClientProperties.getConnectionTimeout();
//        Boolean disableSslValidation = httpClientProperties.isDisableSslValidation();
//        return httpClientFactory.createBuilder(disableSslValidation).connectTimeout((long) connectTimeout, TimeUnit.MILLISECONDS).followRedirects(followRedirects).connectionPool(connectionPool).build();
//
//    }
//
////    @PreDestroy
////    public void destroy() {
////        if (this.okHttpClient != null) {
////            this.okHttpClient.dispatcher().executorService().shutdown();
////            this.okHttpClient.connectionPool().evictAll();
////        }
////
////    }
//
////    @Bean
////    @ConditionalOnMissingBean({Client.class})
////    public Client feignClient(okhttp3.OkHttpClient client) {
////        return new OkHttpClient(client);
////    }
//
//
    @Bean
    @ConditionalOnMissingBean
    public Client feignClient(SpringClientFactory clientFactory) {
        return new LoadBalancerFeignClient(new Client.Default(null, null),
                cachingSpringLoadBalancerFactory, clientFactory);
    }
////    @Bean
////    @ConditionalOnMissingBean
////    public Client feignClient(SpringClientFactory clientFactory, HttpClient httpClient) {
////        OkHttpClient delegate = new OkHttpClient (httpClient);
////        return new LoadBalancerFeignClient(new Client.Default(null, null),
////                cachingSpringLoadBalancerFactory, clientFactory);
////    }
//
    //@Bean
//    @ConditionalOnMissingBean({Client.class})
//    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory, SpringClientFactory clientFactory, okhttp3.OkHttpClient client)
//    {
//        OkHttpClient delegate = new OkHttpClient (client);
//        return new LoadBalancerFeignClient(delegate, cachingFactory, clientFactory); // 进行包装
//    }
}
