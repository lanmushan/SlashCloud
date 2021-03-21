package site.lanmushan.cms;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ResourceBundle;

/**
 * 哈哈
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages = "site.lanmushan")
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@MapperScan(value = "site.lanmushan.auth.mapper")
public class CmsMainApplication {
    static {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appName = bundle.getString("spring.application.name");
        System.setProperty("appName", appName);
    }
    public static void main(String[] args) {

        SpringApplication.run(CmsMainApplication.class, args);
    }

}
