package site.lanmushan.framework.authorization;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import site.lanmushan.framework.authorization.util.TokenUtil;
import site.lanmushan.framework.cypher.md5.MD5Util;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.exception.OperateException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author dy
 * @Date 2020/6/15 22:53
 * @Version 1.0
 */
@Slf4j
public class CurrentUserUtil {
    public final static String tokenSecretKey = "cvj5^2)(136aje,";
    public final static String USER_KEY = "currentUser";
    public final static String AUTHORIZATION = "authorization";

    public static String createPassword(String oldPassword, String salt) {
        return MD5Util.createMD5AndSalt(oldPassword, salt, 3);
    }

    public static boolean isLogin() {
        CurrentUser currentUser = getCurrentUser();
        return currentUser == null ? false : true;
    }


    /**
     * 获取当前用户的token
     *
     * @return
     */
    public static String createToken(CurrentUser currentUser) {
        return TokenUtil.createToekn(currentUser, tokenSecretKey);
    }

    public static CurrentUser getCurrentUser(String token) {
        try {
            String jsonStr = TokenUtil.resolveToken(token, tokenSecretKey);
            return JSON.parseObject(jsonStr, CurrentUser.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }

    /**
     * 只能在含有RequestContextHolder的依赖下使用
     *
     * @return
     */
    public static CurrentUser getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(AUTHORIZATION);
        CurrentUser currentUser = getCurrentUser(token);
        if (currentUser == null) {
            throw new OperateException("未登录", HTTPCode.D600);
        }
        return currentUser;
    }

    public static List<String> getUserApis(CurrentUser currentUser) {
        List<String> list = new ArrayList<>();
        list.add("/api/test/test");
        return list;
    }

    public static List<String> saveUserApis(CurrentUser currentUser, List<String> apiList) {
        return null;
    }

    public static void main(String args[]) {
        CurrentUser currentUser = new CurrentUser();
        currentUser.setAccount("asdfasfsa");
        currentUser.setNickName("xxxxx");
        currentUser.setUserId(1000000L);
        currentUser.setSex(1);
        String token = CurrentUserUtil.createToken(currentUser);
        System.out.println(token);
        CurrentUserUtil.getCurrentUser(token);
    }
}
