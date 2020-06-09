package com.lanmushan.sysservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.File;

/**
 * @author Administrator
 */

@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@SpringBootApplication(scanBasePackages = "com.lanmushan.*.**")
public class SysServiceApplication {

    public static void main(String[] args) {
       SpringApplication.run(SysServiceApplication.class, args);
    }
    static {
        File file=new File("");
    }
}
