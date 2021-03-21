package site.lanmushan.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ResourceBundle;
@SpringBootApplication(scanBasePackages = "site.lanmushan")
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@MapperScan(value = "site.lanmushan.auth.mapper")
public class SlashSysAppApplication {
    static {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appName = bundle.getString("spring.application.name");
        System.setProperty("appName", appName);
    }
    public static void main(String[] args) {
        SpringApplication.run(SlashSysAppApplication.class, args);
    }

}
