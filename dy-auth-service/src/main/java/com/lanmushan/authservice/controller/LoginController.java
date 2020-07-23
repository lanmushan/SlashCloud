package com.lanmushan.authservice.controller;


import com.lanmushan.authservice.bo.BUserLogin;
import com.lanmushan.authservice.bo.BoAuthTbUserLoginLog;
import com.lanmushan.authservice.entity.AuthTbUser;
import com.lanmushan.authservice.mapper.AuthTbUserMapper;
import com.lanmushan.authservice.service.AuthTbUserLoginLogService;
import com.lanmushan.framework.constant.StateTypeConstant;
import com.lanmushan.framework.dto.Message;
import com.lanmushan.framework.entity.CurrentUser;
import com.lanmushan.framework.shiro.CustomUsernamePasswordToken;
import com.lanmushan.framework.util.CurrentUserUtil;
import com.lanmushan.framework.util.IpUtil;
import com.lanmushan.framework.util.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录控制器
 * @author Administrator
 */
@RestController
@RequestMapping("/authLogin")
@Slf4j
public class LoginController {
    @Autowired
    private AuthTbUserMapper authUserMapper;
    @Autowired
    private AuthTbUserLoginLogService loginService;

    @RequestMapping("/loginOut")
    public Message loginOut(HttpSession session) {
        Message msg = new Message();
        session.invalidate();
        msg.success("退出成功");
        return msg;
    }

    /**
     * 后台管理登录方法
     *
     * @param account
     * @param request
     * @return
     */
    @RequestMapping("/loginManage")
    public Message userLogin(@RequestBody BUserLogin account, HttpServletRequest request) {
        Message msg = new Message();
        BoAuthTbUserLoginLog loginLog = new BoAuthTbUserLoginLog();
        loginLog.setLoginSource("后台登录");
        loginLog.setLoginName(account.getAccount());
        loginLog.setCreateTime(DateUtil.now());
        try {
            loginLog.setLoginIp(IpUtil.getRemoteHost(request));
            AuthTbUser tbUser = authUserMapper.selectLoginUser(account.getAccount());
            if (tbUser == null) {
                msg.error("账户不存在");
                return msg;
            }
            loginLog.setFkUserId(tbUser.getId());
            if (tbUser.getIsLock().equals(StateTypeConstant.DISABLED)) {
                msg.error("账号已被锁定，请联系管理员");
                loginLog.setLoginMsg(msg.getMsg());
                return msg;
            }
            Subject subject = SecurityUtils.getSubject();
            CustomUsernamePasswordToken customUsernamePasswordToken = new CustomUsernamePasswordToken(account.getAccount(), account.getPassword());
            //带上数据库密码
            customUsernamePasswordToken.setDpassword(tbUser.getLoginPassword());
            customUsernamePasswordToken.setSalt(tbUser.getSalt());
            subject.login(customUsernamePasswordToken);
            /**设置一些常用参数到当前用户，方便使用*/
            CurrentUser currentUser=new CurrentUser();
            currentUser.setDeptId(tbUser.getDeptId());
            currentUser.setHeadImgAddress(tbUser.getHeadImgAddress());

            currentUser.setNickName(tbUser.getNickName());
            currentUser.setSex(tbUser.getSex());
            currentUser.setUsername(tbUser.getUsername());
            CurrentUserUtil.setCurrentUser(currentUser);
            msg.setRow(CurrentUserUtil.getToken());
            loginLog.setLoginMsg(msg.getMsg());
        } catch (IncorrectCredentialsException e) {
            msg.error("登录失败账号或密码错误");
            loginLog.setLoginMsg(msg.getMsg());
            return msg;
        }finally {
            loginService.insertService(loginLog);
        }
        return msg;
    }
}
