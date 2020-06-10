package com.lanmushan.sysservice;
import com.lanmushan.framework.util.ConstantResource;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Administrator
 */

@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@SpringBootApplication(scanBasePackages = "com.lanmushan.*.**")
public class SysServiceApplication {

    public static void main(String[] args) {

        ThreadContext.put("appName", ConstantResource.bulid("application.properties").getConstant("spring.application.name"));
        SpringApplication.run(SysServiceApplication.class, args);

    }

}
