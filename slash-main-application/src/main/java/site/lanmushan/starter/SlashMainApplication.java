package site.lanmushan.starter;

import com.alibaba.cloud.seata.GlobalTransactionAutoConfiguration;
import com.alibaba.cloud.seata.web.SeataHandlerInterceptorConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ResourceBundle;

/**
 * 哈哈
 *
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages = "site.lanmushan", exclude = {SeataHandlerInterceptorConfiguration.class, GlobalTransactionAutoConfiguration.class})
@MapperScan(value = {"site.lanmushan.*.mapper", "site.lanmushan.framework.query.mapper"})
@Controller
@ServletComponentScan
public class SlashMainApplication {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appName = bundle.getString("spring.application.name");
        System.setProperty("appName", appName);
        SpringApplication.run(SlashMainApplication.class, args);
    }

}
