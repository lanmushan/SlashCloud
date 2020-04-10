package com.lanmushan.dysysservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages = "com.lanmushan.*.**")
public class DySysserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DySysserviceApplication.class, args);
    }

}
