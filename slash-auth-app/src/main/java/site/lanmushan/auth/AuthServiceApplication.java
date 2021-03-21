package site.lanmushan.auth;


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
@EnableFeignClients(basePackages = {"site.lanmushan.sys.api"})
@MapperScan(value = {"site.lanmushan.*.mapper","site.lanmushan.framework.query.mapper"})
public class AuthServiceApplication {
    static {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appName = bundle.getString("spring.application.name");
        System.setProperty("appName", appName);
    }
    public static void main(String[] args) {

        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
