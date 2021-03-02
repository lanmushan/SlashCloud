package site.lanmushan.sys.app;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ResourceBundle;

@SpringBootApplication
public class SlashSysAppApplication {

    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appName = bundle.getString("spring.application.name");
        ThreadContext.put("appName", appName);
        SpringApplication.run(SlashSysAppApplication.class, args);
    }

}
