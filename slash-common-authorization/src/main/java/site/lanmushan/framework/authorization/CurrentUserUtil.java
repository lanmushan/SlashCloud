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
    /**过期时间*/
    private final static int AUTHORIZATION_EXPIRATION_TIME = 5000;
    public final static String REDIS_ONLINE_USER_KEY_PREFIX = "ONLINE_USER|";
    public final static String REDIS_API_HASH_KEY = "REDIS_API_HASH_KEY";

    private static RedisTemplate<Object, Object> getRedisTemplate() {
        return ApplicationUtil.getRedisTemplate();
    }

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
        return TokenUtil.createToken(currentUser, tokenSecretKey);
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
        String redisUserKey = REDIS_ONLINE_USER_KEY_PREFIX + currentUser.getUserId();
        if (getRedisTemplate().hasKey(redisUserKey)) {
            request.setAttribute(USER_KEY, currentUser);
            getRedisTemplate().expire(redisUserKey, AUTHORIZATION_EXPIRATION_TIME, TimeUnit.MILLISECONDS);
        } else {
            throw new OperateException("登录过期", HTTPCode.D600);
        }
        return currentUser;
    }
    public static Boolean currentUserHasUriPermissions(String uri){
       Object value=  getRedisTemplate().opsForHash().get(REDIS_API_HASH_KEY,uri);
       log.info("获取到的权限{}",value);
       return true;
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
