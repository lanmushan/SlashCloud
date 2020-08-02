package site.lanmushan.gatewayservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author Administrator
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class GatewayServiceApplication {

    public static void main(String[] args) {
        log.info("网关服务启动成功");
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

}
