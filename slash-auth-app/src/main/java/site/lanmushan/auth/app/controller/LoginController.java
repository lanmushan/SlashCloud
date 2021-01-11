package site.lanmushan.auth.app.controller;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import site.lanmushan.auth.api.bo.BUserLogin;
import site.lanmushan.auth.api.bo.BoAuthTbUserLoginLog;
import site.lanmushan.auth.api.constant.ResourceConstant;
import site.lanmushan.auth.api.entity.AuthTbResource;
import site.lanmushan.auth.api.entity.AuthTbRole;
import site.lanmushan.auth.api.entity.AuthTbUser;

import site.lanmushan.auth.mapper.AuthTbResourceMapper;
import site.lanmushan.auth.mapper.AuthTbRoleMapper;
import site.lanmushan.auth.mapper.AuthTbUserMapper;
import site.lanmushan.auth.service.AuthTbUserLoginLogService;
import site.lanmushan.framework.authorization.CurrentUser;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.cypher.md5.MD5Util;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.cypher.base64.Base64Util;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.util.List;
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
@Api(tags = "登录服务接口")
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
    @PostMapping("/loginManage")
    public Message userLogin(@RequestBody BUserLogin account, HttpServletRequest request) {
        String ip = IpUtil.getRemoteHost(request);
        String os = ServletUtil.getLoginOs(request);
        String browser = ServletUtil.getLoginBrowser(request);
        /**以ip，操作系统，浏览器，账号为基础生成uuid,在登录接口验证*/
        String uuid=MD5Util.createMD532(ip+os+browser+account.getAccount());

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
    public Message getSysManageLoginCode(String account, HttpServletResponse response, HttpServletRequest request) {
        Message msg = Message.getInstance();
        try {
            if(StringUtils.isEmpty(account))
            {
                msg.error("登录账号不能为空");
                return msg;
            }
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
            /**以ip，操作系统，浏览器，账号为基础生成uuid,在登录接口验证*/
            String uuid=MD5Util.createMD532(ip+os+browser+account);
            redisTemplate.opsForValue().set(GlobalConstant.IMG_VERIFICATION_CODE + uuid, code, 300, TimeUnit.SECONDS);
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
