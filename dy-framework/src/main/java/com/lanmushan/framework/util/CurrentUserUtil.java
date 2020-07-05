package com.lanmushan.framework.util;

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
    public final static String USER_KEY="currentUser";
    public static CurrentUser getCurrentUser(){
        Session session =  SecurityUtils.getSubject().getSession();
        CurrentUser currentUser= (CurrentUser) session.getAttribute(USER_KEY);
        if(currentUser==null)
        {
            throw new OperateException("请先登录后进行操作", HTTPCode.D600);
        }
        return currentUser;
    }
}
