package site.lanmushan.authservice.controller;


import com.alibaba.fastjson.JSONObject;
import site.lanmushan.authservice.bo.BUserLogin;
import site.lanmushan.authservice.bo.BoAuthTbUserLoginLog;
import site.lanmushan.authservice.entity.AuthTbUser;
import site.lanmushan.authservice.mapper.AuthTbUserMapper;
import site.lanmushan.authservice.service.AuthTbUserLoginLogService;
import site.lanmushan.cypher.base64.Base64Util;
import site.lanmushan.framework.constant.GlobalConstant;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.constant.StateTypeConstant;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.entity.CurrentUser;
import site.lanmushan.framework.shiro.CustomUsernamePasswordToken;
import site.lanmushan.framework.util.CurrentUserUtil;
import site.lanmushan.framework.util.IpUtil;
import site.lanmushan.framework.util.ServletUtil;
import site.lanmushan.framework.util.VerifyCodeUtils;
import site.lanmushan.framework.util.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 登录控制器
 *
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
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @PostMapping("/loginOut")
    public Message loginOut(HttpSession session) {
        Message msg = Message.getInstance();
        redisTemplate.expire(GlobalConstant.SESSION_ID_PREFIX + session.getId(), 5000, TimeUnit.MILLISECONDS);
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
            loginLog.setLoginOs(ServletUtil.getLoginOs(request));
            loginLog.setLoginBrowser(ServletUtil.getLoginBrowser(request));
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
            CurrentUser currentUser = new CurrentUser();
            currentUser.setDeptId(tbUser.getDeptId());
            currentUser.setHeadImgAddress(tbUser.getHeadImgAddress());
            currentUser.setNickName(tbUser.getNickName());
            currentUser.setSex(tbUser.getSex());
            currentUser.setUsername(tbUser.getUsername());
            CurrentUserUtil.setCurrentUser(currentUser);
            msg.setRow(CurrentUserUtil.getToken()).success("登录成功");
            loginLog.setLoginMsg(msg.getMsg());
        } catch (IncorrectCredentialsException e) {
            log.error(e.getLocalizedMessage(), e);
            msg.error("登录失败账号或密码错误");
            loginLog.setLoginMsg(msg.getMsg());
            return msg;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            msg.error("登录未知错误").setCode(HTTPCode.S500.code);
            loginLog.setLoginMsg(msg.getMsg());
        } finally {
            loginService.insertService(loginLog);
        }
        return msg;
    }

    /**
     * 同时支持token和cookie验证，如果客户端不携带cookie过来第一次创建的session就会浪费
     * 所以获取验证码时讲原有的session清除掉
     * @param response
     * @param request
     * @param session
     * @return
     */
    @GetMapping("/selectVerificationCode")
    public Message getSysManageLoginCode(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        Message msg = Message.getInstance();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            String code = VerifyCodeUtils.outputVerifyImage(80, 30, byteArrayOutputStream, 4);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String base64 = Base64Util.encodeToString(bytes);
            base64 = base64.replaceAll("\n", "").replaceAll("\r", "");
            session.setAttribute(GlobalConstant.VERIFICATION_CODE, code.toLowerCase());
            JSONObject data=new JSONObject();
            data.put("img","data:image/png;base64," + base64);
            String uuid= UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(GlobalConstant.VERIFICATION_CODE+uuid,code,300, TimeUnit.SECONDS);
            redisTemplate.expire(GlobalConstant.SESSION_ID_PREFIX+session.getId(),5000 , TimeUnit.MILLISECONDS);
            data.put("uid",uuid);
            msg.setRow(data);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return msg;
    }

}
