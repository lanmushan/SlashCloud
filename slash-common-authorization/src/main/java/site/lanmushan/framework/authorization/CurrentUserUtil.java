package site.lanmushan.framework.authorization;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import site.lanmushan.framework.authorization.util.TokenUtil;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.cypher.md5.MD5Util;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.util.ApplicationUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public final static String ADMIN_CODE = "admin";
    public final static String ANON_CODE = "anon";
    /**
     * 过期时间
     */
    private final static int AUTHORIZATION_EXPIRATION_TIME = 30 * 60 * 1000;
    public final static String REDIS_ONLINE_USER_KEY_PREFIX = "ONLINE_USER|";
    public final static String REDIS_API_HASH_KEY = "REDIS_API_HASH_KEY";

    private static RedisTemplate<String, String> getRedisTemplate() {
        return ApplicationUtil.getStringRedisTemplate();
    }

    public static String createPassword(String oldPassword, String salt) {
        return MD5Util.createMD5AndSalt(oldPassword, salt, 3);
    }

    public static boolean isLogin() {
        CurrentUser currentUser = getCurrentUser();
        return currentUser != null;
    }

    /**
     * 获取当前用户的token
     *
     * @return
     */
    public static String createToken(CurrentUser currentUser) {
        return TokenUtil.createToken(currentUser, tokenSecretKey);
    }

    public static CurrentUser getCurrentUser(String token) {
        try {
            String jsonStr = TokenUtil.resolveToken(token, tokenSecretKey);
            return JSON.parseObject(jsonStr, CurrentUser.class);
        } catch (Exception e) {
            //  log.error(e.getMessage(), e);
            return null;
        }

    }

    /**
     * 只能在含有RequestContextHolder的依赖下使用
     * 1.首先在request.attribute中获取用户信息
     * 2.在token中获取
     * 3.在token中获取成功过后不直接返回，判断redis中是否过期；过期抛出异常，没有过期进行续期
     *
     * @return
     */
    public static CurrentUser getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object currentUserObj = request.getAttribute(USER_KEY);
        if (currentUserObj != null) {
            return (CurrentUser) currentUserObj;
        }
        String token = request.getHeader(AUTHORIZATION);
        CurrentUser currentUser = getCurrentUser(token);
        if (currentUser == null) {
            throw new OperateException("未登录", HTTPCode.D600);
        }
        if (isLoginOverdue(currentUser, token)) {
            request.setAttribute(USER_KEY, currentUser);
        }
        return currentUser;
    }

    public static boolean isLoginOverdue(CurrentUser currentUser, String token) {
        if (currentUser == null) {
            throw new OperateException("未登录", HTTPCode.D600);

        }
        String redisUserKey = REDIS_ONLINE_USER_KEY_PREFIX + currentUser.getAccount();
        Object value = getRedisTemplate().opsForValue().get(redisUserKey);
        if (value == null) {
            throw new OperateException("登录过期", HTTPCode.D600);
        }
        //用户只能在一个地方登陆，如果允许多端登录修改规则
        if (token.equals(value)) {
            getRedisTemplate().expire(redisUserKey, AUTHORIZATION_EXPIRATION_TIME, TimeUnit.MILLISECONDS);
        } else {
            throw new OperateException("您的账号已经在其他地方登录或登录过期", HTTPCode.D600);
        }
        return true;
    }

    public static void saveUserToRedis(CurrentUser currentUser, String token) {
        String redisUserKey = REDIS_ONLINE_USER_KEY_PREFIX + currentUser.getAccount();
        getRedisTemplate().opsForValue().set(redisUserKey, token, AUTHORIZATION_EXPIRATION_TIME, TimeUnit.MILLISECONDS);
    }

    public static Boolean currentUserHasUriPermissions(String uri, CurrentUser currentUser) {
        Object value = getRedisTemplate().opsForHash().get(REDIS_API_HASH_KEY, uri);
        if (value == null) {
            return false;
        }

        List roleCodes = Arrays.asList(((String) value).split(","));
        if(roleCodes.contains(ANON_CODE))
        {
            return true;
        }
        if(roleCodes.contains(ANON_CODE))
        {
            return true;
        }
        if(currentUser==null)
        {
            return false;
        }
        return roleCodes.contains(ANON_CODE) || roleCodes.containsAll(currentUser.getRoleCodes());
    }

    public static Boolean currentUserHasUriPermissions(String uri) {
        CurrentUser currentUser = getCurrentUser();
        return currentUserHasUriPermissions(uri, currentUser);
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
