package site.lanmushan.auth.controller.app;



import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ResourceBundle;


/**
 * 用户权限服务
 *
 * @author dy
 */
@SpringBootApplication(scanBasePackages = "site.lanmushan")
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@MapperScan(value = "site.lanmushan.auth.mapper")
public class AuthServiceApplication {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appName = bundle.getString("spring.application.name");
        ThreadContext.put("appName", appName);
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
