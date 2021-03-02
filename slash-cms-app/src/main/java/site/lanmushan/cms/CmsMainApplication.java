package site.lanmushan.cms;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ResourceBundle;

/**
 * 哈哈
 */
@SpringBootApplication(scanBasePackages = "site.lanmushan")
@MapperScan(value = {"site.lanmushan.*.mapper"})
public class CmsMainApplication {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appName = bundle.getString("spring.application.name");
        ThreadContext.put("appName", appName);
        SpringApplication.run(CmsMainApplication.class, args);
    }

}
