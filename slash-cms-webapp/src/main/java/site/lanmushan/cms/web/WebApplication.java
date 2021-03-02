package site.lanmushan.cms.web;

import org.apache.logging.log4j.ThreadContext;
import org.jboss.logging.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import site.lanmushan.framework.query.controller.QueryController;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ResourceBundle;

/**
 * 哈哈
 *
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages = "site.lanmushan")
@MapperScan(value = {"site.lanmushan.*.mapper"})
public class WebApplication {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appName = bundle.getString("spring.application.name");
        System.setProperty("appName", appName);
        SpringApplication.run(WebApplication.class, args);
    }

}
