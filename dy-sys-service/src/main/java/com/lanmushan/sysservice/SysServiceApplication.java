package com.lanmushan.sysservice;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ResourceBundle;

/**
 * @Author dy
 * @Date 2020/7/13 20:03
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = "com.lanmushan")
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
public class SysServiceApplication {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appName=bundle.getString("spring.application.name");
        ThreadContext.put("appName", appName);
        SpringApplication.run(SysServiceApplication.class, args);
    }
}
