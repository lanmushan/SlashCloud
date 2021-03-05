package site.lanmushan.resource;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.ResourceBundle;

/**
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages = "site.lanmushan")
public class SlashResourceAppApplication {

    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appName = bundle.getString("spring.application.name");
        System.setProperty("appName", appName);
        SpringApplication.run(SlashResourceAppApplication.class, args);
    }

}
