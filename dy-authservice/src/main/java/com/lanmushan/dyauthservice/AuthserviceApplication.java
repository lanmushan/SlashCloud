package com.lanmushan.dyauthservice;

import com.lanmushan.framework.configure.datasource.DruidConfiguration;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ResourceBundle;

@SpringBootApplication(scanBasePackages = "com.lanmushan.*.**")
@EnableDiscoveryClient
@EnableHystrix
public class AuthserviceApplication {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appName=bundle.getString("spring.application.name");
        ThreadContext.put("appName", appName);
        SpringApplication.run(AuthserviceApplication.class, args);
    }
}
