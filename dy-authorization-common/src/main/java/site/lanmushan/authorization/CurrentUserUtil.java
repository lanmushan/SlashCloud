package site.lanmushan.authorization;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import site.lanmushan.authorization.util.TokenUtil;
import site.lanmushan.cypher.base64.Base64Util;

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
    public final static String AUTHORIZATION="authorization";
    public static CurrentUser createCurrentUser() {
        return new CurrentUser();
    }
    public static boolean isLogin(String token) {
       if(StringUtils.isEmpty(token))
       {
           return false;
       }
       if(null!= getCurrentUser(token))
       {
           return true;
       }
       return false;
    }

    public static boolean isAdmin() {

    return false;
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
    public static String createToekn(CurrentUser currentUser) {
        return TokenUtil.createToekn(currentUser, tokenSecretKey);
    }
    public static CurrentUser getCurrentUser() {
return null;
    }
    public static CurrentUser getCurrentUser(String token) {
        try {
            String jsonStr = TokenUtil.resolveToken(token, tokenSecretKey);
            return JSON.parseObject(jsonStr, CurrentUser.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
    public static List<String> getCurrentUserApis(CurrentUser currentUser)
    {
        List<String> list=new ArrayList<>();
        list.add("/api/test/test");
        return list;
    }
    public static List<String> savaCurrentUserApis(CurrentUser currentUser,List<String> apiList)
    {

         return null;
    }
    public static String toBase64(CurrentUser currentUser){
          byte[]bytes=  SerializationUtils.serialize(currentUser);
          return   Base64Util.encodeToString(bytes).replaceAll("\r","").replaceAll("\n","");
    }
    public static CurrentUser toCurrentUser(String base64){
        byte[]bytes=  SerializationUtils.deserialize(Base64Util.decodeToByte(base64));
       return SerializationUtils.deserialize(bytes);
    }
    public static void main(String args[]) {
        CurrentUser currentUser = new CurrentUser();
        currentUser.setAccount("asdfasfsa");
        currentUser.setNickName("xxxxx");
        currentUser.setUserId(1000000L);
        currentUser.setSex(1);
        String token = CurrentUserUtil.createToekn(currentUser);
        System.out.println(token);
        CurrentUserUtil.getCurrentUser(token);
    }
}
