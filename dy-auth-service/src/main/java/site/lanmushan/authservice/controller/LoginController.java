package site.lanmushan.authservice.controller;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import site.lanmushan.authorization.CurrentUser;
import site.lanmushan.authorization.CurrentUserUtil;
import site.lanmushan.authservice.bo.BUserLogin;
import site.lanmushan.authservice.bo.BoAuthTbUserLoginLog;
import site.lanmushan.authservice.constant.ResourceConstant;
import site.lanmushan.authservice.entity.AuthTbResource;
import site.lanmushan.authservice.entity.AuthTbRole;
import site.lanmushan.authservice.entity.AuthTbUser;
import site.lanmushan.authservice.mapper.AuthTbResourceMapper;
import site.lanmushan.authservice.mapper.AuthTbRoleMapper;
import site.lanmushan.authservice.mapper.AuthTbUserMapper;
import site.lanmushan.authservice.service.AuthTbUserLoginLogService;
import site.lanmushan.cypher.md5.MD5Util;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.cypher.base64.Base64Util;
import site.lanmushan.framework.constant.GlobalConstant;
import site.lanmushan.framework.constant.StateTypeConstant;
import site.lanmushan.framework.dto.Message;

import site.lanmushan.framework.util.IpUtil;
import site.lanmushan.framework.util.ServletUtil;
import site.lanmushan.framework.util.VerifyCodeUtils;
import site.lanmushan.framework.util.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

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
    private AuthTbRoleMapper authTbRoleMapper;
    @Autowired
    private AuthTbResourceMapper authTbResourceMapper;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

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
            String password = CurrentUserUtil.createPassword(account.getPassword(), tbUser.getSalt());
            if (!tbUser.getLoginPassword().equals(password)) {
                msg.error("账号或密码错误");
                loginLog.setLoginMsg(msg.getMsg());
                return msg;
            }
            String token = handerCurentUser(tbUser);
            msg.setRow(token).success("登录成功");
            return msg;
        } finally {
            loginService.insertService(loginLog);
        }

    }

    private String handerCurentUser(AuthTbUser tbUser) {
        /**设置一些常用参数到当前用户，方便使用*/
        CurrentUser currentUser = new CurrentUser();
        currentUser.setAccount(tbUser.getAccount());
        currentUser.setUserId(tbUser.getId());
        currentUser.setDeptId(tbUser.getDeptId());
        currentUser.setHeadImgAddress(tbUser.getHeadImgAddress());
        currentUser.setNickName(tbUser.getNickName());
        currentUser.setSex(tbUser.getSex());
        currentUser.setUsername(tbUser.getUsername());
        /**查询并设置所有的角色*/
        List<AuthTbRole> list = authTbRoleMapper.selectRolesByUserId(tbUser.getId());
        List<String> roleCodes = list.stream().map(AuthTbRole::getRoleCode).collect(toList());
        String roleCodeJoin = StringUtils.join(roleCodes, ",");
        currentUser.setRoleCodes(roleCodes);

        /**查询并设置所有的api权限*/
        List<AuthTbResource> resourceList = authTbResourceMapper.selectResourceByRoleCodes(roleCodeJoin, ResourceConstant.RESOURCE_API);
        List<String> apiList = resourceList.stream().map(AuthTbResource::getResourceUrl).collect(toList());

        String token = CurrentUserUtil.createToken(currentUser);
        return token;
    }

    /**
     *
     *
     * 获取验证码时必须传入账号
     * @param response
     * @param request

     * @return
     */
    @GetMapping("/selectVerificationCode")
    public Message getSysManageLoginCode(BUserLogin account, HttpServletResponse response, HttpServletRequest request) {
        Message msg = Message.getInstance();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            String code = VerifyCodeUtils.outputVerifyImage(80, 30, byteArrayOutputStream, 4);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String base64 = Base64Util.encodeToString(bytes);
            base64 = base64.replaceAll("\n", "").replaceAll("\r", "");
            JSONObject data = new JSONObject();
            data.put("img", "data:image/png;base64," + base64);
            String ip = IpUtil.getRemoteHost(request);
            String os = ServletUtil.getLoginOs(request);
            String browser = ServletUtil.getLoginBrowser(request);
            /**以ip，操作系统，浏览器，账号为基础生成uuid*/
            String uuid=MD5Util.createMD532(ip+os+browser+account.getAccount());
            redisTemplate.opsForValue().set(GlobalConstant.IMG_VERIFICATION_CODE + uuid, code, 300, TimeUnit.SECONDS);
            data.put("uid", uuid);
            msg.setRow(data);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return msg;
    }

    @GetMapping("/notLogin")
    public Message NotLogin() {
        Message msg = Message.getInstance();
        msg.setHttpCode(HTTPCode.D600);
        return msg;
    }
}
