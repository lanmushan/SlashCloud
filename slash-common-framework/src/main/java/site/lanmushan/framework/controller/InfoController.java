package site.lanmushan.framework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lanmushan.framework.dto.Message;

@RequestMapping("/info")
@RestController
public class InfoController {
    @GetMapping("/test")
    public Message test(){
        return Message.getInstance().success("访问成功");
    }
}
