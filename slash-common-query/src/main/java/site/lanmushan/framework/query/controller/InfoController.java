package site.lanmushan.framework.query.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lanmushan.framework.dto.Message;

@RestController
@Slf4j
public class InfoController {
    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping("/info")
    public Message test() {
        log.info("健康检查");
        return Message.getInstance().success(appName + "应用启动成功");
    }
}
