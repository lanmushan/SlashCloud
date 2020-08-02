package site.lanmushan.gatewayservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.Message;

@RestController
public class ErrorController {
    @RequestMapping("unauthorized")
    public Message unauthorized(){
        Message msg=    Message.getInstance();
        msg.setHttpCode(HTTPCode.D601);
        return msg;
    }
    @RequestMapping("unLogin")
    public Message unLogin(){
        Message msg=    Message.getInstance();
        msg.setHttpCode(HTTPCode.D600);
        return msg;
    }
}
