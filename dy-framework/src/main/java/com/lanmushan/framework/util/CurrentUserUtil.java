package com.lanmushan.framework.util;

import com.lanmushan.cypher.aes.AesUtil;
import com.lanmushan.framework.constant.HTTPCode;
import com.lanmushan.framework.entity.CurrentUser;
import com.lanmushan.framework.exception.OperateException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * @Author dy
 * @Date 2020/6/15 22:53
 * @Version 1.0
 */
public class CurrentUserUtil {
    public final static String USER_KEY = "currentUser";
    /**
     * 登录加密的KEy
     */
    public static final String USER_TOKEN_SECRETKEY = "sfT(!aslhlTf45.4765";

    public static CurrentUser getCurrentUser() {
        Session session = SecurityUtils.getSubject().getSession();
        CurrentUser currentUser = (CurrentUser) session.getAttribute(USER_KEY);
        if (currentUser == null) {
            throw new OperateException("请先登录后进行操作", HTTPCode.D600);
        }
        return currentUser;
    }

    /**
     *设置当前用户
     * @param currentUser
     */
    public static void setCurrentUser(CurrentUser currentUser) {
        Session session = SecurityUtils.getSubject().getSession();
         session.setAttribute(USER_KEY,currentUser);
        if (currentUser == null) {
            throw new OperateException("请先登录后进行操作", HTTPCode.D600);
        }
    }
    /**
     * 获取当前用户的token
     *
     * @return
     */
    public static String getToken() {
        Session session = SecurityUtils.getSubject().getSession();
        /**这里直接使用sessionId加密作为token，可以进行改造*/
        AesUtil aesUtil = new AesUtil();
        aesUtil.setSecretKey(USER_TOKEN_SECRETKEY);
        String token = aesUtil.encryptToBase64(session.getId().toString());
        return token;
    }
}
