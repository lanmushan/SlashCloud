package com.lanmushan.frameworktest;

import com.lanmushan.framework.controller.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.lanmushan.*.*")
@RestController
public class FrameworkTestApplication extends BaseController {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkTestApplication.class, args);
    }

}
