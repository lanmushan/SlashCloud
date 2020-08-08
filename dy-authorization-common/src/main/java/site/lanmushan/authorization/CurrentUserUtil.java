package site.lanmushan.authorization;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import site.lanmushan.authorization.util.TokenUtil;
import site.lanmushan.cypher.md5.MD5Util;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.utils.OperateException;

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

    public static boolean isAdmin() {
        CurrentUser currentUser = getCurrentUser();
        if (null == currentUser) {
            throw new OperateException("用户未登录", HTTPCode.D600);
        }
        return isAdmin(currentUser);
    }

    public static boolean isAdmin(CurrentUser currentUser) {
        List<String> roleList = currentUser.getRoleCodes();
        if (null == roleList || roleList.size() == 0) {
            return false;
        }
        return roleList.contains("admin");
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
            if(null==jsonStr)
            {
                throw new OperateException("未登录",HTTPCode.D600);
            }
            return JSON.parseObject(jsonStr, CurrentUser.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OperateException("未登录",HTTPCode.D600);

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
            return getCurrentUser(token);

    }

    public static List<String> getCurrentUserApis(CurrentUser currentUser) {
        List<String> list = new ArrayList<>();
        list.add("/api/test/test");
        return list;
    }

    public static List<String> savaCurrentUserApis(CurrentUser currentUser, List<String> apiList) {
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
