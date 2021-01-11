package site.lanmushan.framework.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class ApplicationRunnerInit implements ApplicationRunner {
      private Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("应用程序准备启动");
    }
}
