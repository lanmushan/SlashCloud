package site.lanmushan.framework.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Slf4j
@Component
public class ApplicationRunnerInit implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        log.info("应用程序准备启动");
    }
}
