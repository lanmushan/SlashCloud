package com.lanmushan.dyauthservice;

import com.lanmushan.dyauthservice.rpcservice.DictService;
import com.lanmushan.framework.shiro.ShiroUsernamePasswordToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/Admin/auth")
public class InfoController {
    @Autowired
    DictService dictService;
    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name, HttpServletRequest request) {
        return "我是客户端authServie返回的数据";
    }
    @RequestMapping("/login")
    public String login(){
        Subject subject = SecurityUtils.getSubject();
        ShiroUsernamePasswordToken shiroUsernamePasswordToken = new ShiroUsernamePasswordToken("111","111");
        subject.login(shiroUsernamePasswordToken);
        return "1111";
    }
    @RequestMapping("/test2")
    public String test2(HttpSession session){
        System.out.println(session.getAttribute("111"));
        session.setAttribute("111",Math.random());
        return "1111";
    }
    @RequestMapping("/test3")
    public String test3(HttpSession session){
        session.setAttribute("aa","写入数据");
        return dictService.sayHiFromClientOne("111");
    }
}
