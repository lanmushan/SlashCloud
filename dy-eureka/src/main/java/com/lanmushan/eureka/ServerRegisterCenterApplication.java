package com.lanmushan.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableEurekaServer
public class ServerRegisterCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerRegisterCenterApplication.class, args);
    }

}
