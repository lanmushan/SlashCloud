package site.lanmushan.gatewayservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ResourceBundle;


/**
 * @author Administrator
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class GatewayServiceApplication {

    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appName=bundle.getString("spring.application.name");
        ThreadContext.put("appName", appName);
        SpringApplication.run(GatewayServiceApplication.class, args);
        log.info("GateWay服务启动成功");
    }

}
