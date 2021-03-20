package site.lanmushan.framework.configure.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Administrator
 */
@Slf4j
@Component
@Order(value = 9999)
public class ApplicationRunnerInit implements ApplicationRunner {
    @Value("${server.port}")
    private int port;

    @Override
    public void run(ApplicationArguments args) {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            addr.getHostAddress();
            log.info("应用程序启动完成 IP:{} PORT：{}", addr.getHostAddress(), port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
