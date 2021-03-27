package site.lanmushan.auth.controller;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.auth.api.bo.BUserLogin;
import site.lanmushan.auth.api.bo.BoAuthTbUserLoginLog;
import site.lanmushan.auth.api.constant.ResourceConstant;
import site.lanmushan.auth.api.entity.AuthTbResource;
import site.lanmushan.auth.api.entity.AuthTbRole;
import site.lanmushan.auth.api.entity.AuthTbUser;
import site.lanmushan.auth.api.vo.VoAuthTbResource;
import site.lanmushan.auth.mapper.AuthTbResourceMapper;
import site.lanmushan.auth.mapper.AuthTbRoleMapper;
import site.lanmushan.auth.mapper.AuthTbUserMapper;
import site.lanmushan.auth.api.req.ModifyPasswordReq;
import site.lanmushan.auth.api.service.AuthTbUserLoginLogService;
import site.lanmushan.auth.api.service.AuthTbUserService;
import site.lanmushan.framework.authorization.CurrentUser;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.constant.GlobalConstant;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.constant.StateTypeConstant;
import site.lanmushan.framework.cypher.base64.Base64Util;
import site.lanmushan.framework.cypher.md5.MD5Util;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.query.controller.BaseController;
import site.lanmushan.framework.query.util.ServletUtil;
import site.lanmushan.framework.util.VerifyCodeUtils;
import site.lanmushan.framework.util.utils.DateUtil;

import javax.management.OperationsException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
public class LoginController extends BaseController {
    @Autowired
    private AuthTbUserMapper authUserMapper;
    @Autowired
    private AuthTbUserLoginLogService loginService;
    @Autowired
    private AuthTbRoleMapper authTbRoleMapper;
    @Autowired
    private AuthTbResourceMapper authTbResourceMapper;
    @Autowired
    private AuthTbUserService authTbUserService;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @PostMapping("/currentUserInfo")
    public Message currentUserInfo(HttpSession session) {
        return Message.getInstance().setRow(CurrentUserUtil.getCurrentUser());
    }

    @PostMapping("/loginOut")
    public Message loginOut(HttpSession session) {
        Message msg = Message.getInstance();
        redisTemplate.expire(GlobalConstant.SESSION_ID_PREFIX + session.getId(), 5000, TimeUnit.MILLISECONDS);
        msg.success("退出成功");
        return msg;
    }

    @PostMapping("/modifyPassword")
    public Message modifyPassword(@RequestBody @Valid ModifyPasswordReq req) {
        Message msg = Message.getInstance();
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser();
        AuthTbUser tbUser = authUserMapper.selectLoginUser(currentUser.getAccount());
        String password = CurrentUserUtil.createPassword(req.getOldPassword(), tbUser.getSalt());
        if (!tbUser.getLoginPassword().equals(password)) {
            msg.error("原密码错误");
            return msg;
        }
        authTbUserService.resetLoginPassword(tbUser.getId(), req.getNewPassword());
        msg.success("密码修改成功");
        return msg;
    }

    /**
     * 后台管理登录方法
     * TODO 暂时没有判断验证码
     *
     * @param account
     * @param request
     * @return
     */
    @PostMapping("/loginManage")
    public Message userLogin(@RequestBody BUserLogin account, HttpServletRequest request) throws OperationsException {
        String ip = ServletUtil.getRemoteHost(request);
        String os = ServletUtil.getLoginOs(request);
        String browser = ServletUtil.getLoginBrowser(request);
        /**以ip，操作系统，浏览器，账号为基础生成uuid,在登录接口验证*/
        String uuid = MD5Util.createMD532(ip + os + browser + account.getAccount());
        Message msg = new Message();
        BoAuthTbUserLoginLog loginLog = new BoAuthTbUserLoginLog();
        loginLog.setLoginSource("后台登录");
        loginLog.setLoginName(account.getAccount());
        loginLog.setCreateTime(DateUtil.now());
        try {
            loginLog.setLoginIp(ServletUtil.getRemoteHost(request));
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
            CurrentUser  currentUser= handerCurentUser(tbUser);
            String token = CurrentUserUtil.createToken(currentUser);
            CurrentUserUtil.saveUserToRedis(currentUser,token);
            loginLog.setLoginMsg("登录成功");
            msg.setRow(token).success("登录成功");
            return msg;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            loginLog.setLoginMsg("系统异常");
            throw new OperationsException("系统异常");
        } finally {
            loginService.insertService(loginLog);
        }

    }

    private CurrentUser handerCurentUser(AuthTbUser tbUser) {
        /**设置一些常用参数到当前用户，方便使用*/
        CurrentUser currentUser = new CurrentUser();
        currentUser.setAccount(tbUser.getAccount());
        currentUser.setUserId(tbUser.getId());
        currentUser.setDeptCode(tbUser.getFkDeptCode());
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
        List<VoAuthTbResource> resourceList = authTbResourceMapper.selectResourceByRoleCodes(roleCodeJoin, ResourceConstant.RESOURCE_API);
        List<String> apiList = resourceList.stream().map(AuthTbResource::getResourceUrl).collect(toList());

      return currentUser;
    }

    /**
     * 获取验证码时必须传入账号
     *
     * @param response
     * @param request
     * @return
     */
    @GetMapping("/selectVerificationCode")
    public Message getSysManageLoginCode(String account, HttpServletResponse response, HttpServletRequest request) {
        Message msg = Message.getInstance();
        try {
            if (StringUtils.isEmpty(account)) {
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
            String ip = ServletUtil.getRemoteHost(request);
            String os = ServletUtil.getLoginOs(request);
            String browser = ServletUtil.getLoginBrowser(request);
            /**以ip，操作系统，浏览器，账号为基础生成uuid,在登录接口验证*/
            String uuid = MD5Util.createMD532(ip + os + browser + account);
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
