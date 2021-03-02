package site.lanmushan.starter;

import com.alibaba.cloud.seata.GlobalTransactionAutoConfiguration;
import com.alibaba.cloud.seata.SeataProperties;
import com.alibaba.cloud.seata.web.SeataHandlerInterceptorConfiguration;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lanmushan.framework.dto.Message;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ResourceBundle;

/**
 * 哈哈
 *
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages = "site.lanmushan", exclude = {SeataHandlerInterceptorConfiguration.class, GlobalTransactionAutoConfiguration.class})
@MapperScan(value = {"site.lanmushan.*.mapper"})
@Controller
public class SlashMainApplication {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String appName = bundle.getString("spring.application.name");
        System.setProperty("appName", appName);
        SpringApplication.run(SlashMainApplication.class, args);
    }

}
