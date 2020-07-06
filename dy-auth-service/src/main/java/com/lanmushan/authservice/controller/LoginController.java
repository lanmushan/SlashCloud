package com.lanmushan.authservice.controller;


import com.lanmushan.authservice.bo.BUserLogin;
import com.lanmushan.authservice.bo.BoAuthTbUserLoginLog;
import com.lanmushan.authservice.constant.AuthConstant;
import com.lanmushan.authservice.entity.AuthTbUser;
import com.lanmushan.authservice.mapper.AuthTbUserMapper;
import com.lanmushan.authservice.service.AuthTbUserLoginLogService;
import com.lanmushan.framework.constant.StateTypeConstant;
import com.lanmushan.framework.dto.Message;
import com.lanmushan.framework.util.IpUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/authLogin")
public class LoginController {
    @Autowired
    private AuthTbUserMapper authUserMapper;
    @Autowired
    private AuthTbUserLoginLogService loginService;
    @RequestMapping("/loginOut")
    public Message loginOut(HttpSession session){
        Message msg=new Message();
        session.invalidate();
        msg.success("退出成功");
        return msg;
    }
    /**
     * 普通用户登录方法
     * @param account
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/userLogin")
    public Message userLogin(@RequestBody BUserLogin account, HttpSession session, HttpServletRequest request) {
        System.out.println("Session"+session.getId());
        BoAuthTbUserLoginLog log = new BoAuthTbUserLoginLog();
        log.setLoginSource("平台登录");
        log.setLoginName(account.getAccount());
        log.setCreateTime(new Date());
        Message msg = new Message();
        log.setLoginIp(IpUtil.getRemoteHost(request));
        Integer loginCount = (Integer) session.getAttribute(AuthConstant.USER_LOGIN_KEY);
        if (loginCount == null) {
            loginCount = 1;
        } else {
            loginCount++;
        }
        session.setAttribute(AuthConstant.USER_LOGIN_KEY, loginCount);
        if (loginCount > 5) {
            msg.error("登录次数过多,请24小时候重试");
            msg.setRow(loginCount);
            return msg;
        }
        AuthTbUser tbUser = authUserMapper.selectLoginUser(account.getAccount());
        if (tbUser == null) {
            msg.error("账号不存在");
            return msg;
        }
        log.setFkUserId(tbUser.getId());
        if (tbUser.getIsLock().equals(StateTypeConstant.DISABLED)) {
            msg.error("账号已被锁定，请联系管理员");
            log.setLoginMsg(msg.getMsg());
            loginService.insertService(log);
            return msg;
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(tbUser.getAccount(), account.getPassword());
            subject.login(usernamePasswordToken);
        } catch (Exception e) {
            e.printStackTrace();
            msg.error("登录失败账号或密码错误");
            log.setLoginMsg(msg.getMsg());
            loginService.insertService(log);
            return msg;
        }
        msg.setRow(session.getId());
        msg.success("登录成功");
        log.setLoginMsg(msg.getMsg());
        loginService.insertService(log);
        return msg;
    }
}
