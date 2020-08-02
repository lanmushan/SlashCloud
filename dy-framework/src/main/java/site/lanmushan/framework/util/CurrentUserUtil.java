package site.lanmushan.framework.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import site.lanmushan.cypher.aes.AesEbcUtil;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.entity.CurrentUser;
import site.lanmushan.framework.exception.OperateException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author dy
 * @Date 2020/6/15 22:53
 * @Version 1.0
 */
@Slf4j
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
    public static boolean isLogin(){

        Session session = SecurityUtils.getSubject().getSession();
        log.info("session{}:",session.getId());
        CurrentUser currentUser = (CurrentUser) session.getAttribute(USER_KEY);
        if (currentUser == null) {
            return  false;
        }else {
            return true;
        }
    }
    /**
     *设置当前用户
     * @param currentUser
     */
    public static void setCurrentUser(CurrentUser currentUser) {

        Session session = SecurityUtils.getSubject().getSession();
        log.info("登录{}:",session.getId());
         session.setAttribute(USER_KEY,currentUser);
    }
    public static boolean isAdmin(){
        CurrentUser currentUser=getCurrentUser();
        List<String> roleList=  currentUser.getRoleCodes();
        if(null==roleList||roleList.size()==0)
        {
            return false;
        }
      return  roleList.contains("admin");
    }
    /**
     * 获取当前用户的token
     *
     * @return
     */
    public static String getToken() {
        JSONObject json=new JSONObject();
        Session session = SecurityUtils.getSubject().getSession();
        json.put("sessionId",session.getId());
        /**这里直接使用sessionId加密作为token，可以进行改造*/
        String token = AesEbcUtil.encryptToHex(json.toJSONString(),USER_TOKEN_SECRETKEY);
        return token;
    }
    public static Serializable getSessionId(String token){
        try {
            String jsonStr= AesEbcUtil.decryptByHex(token,USER_TOKEN_SECRETKEY);
            JSONObject json=JSONObject.parseObject(jsonStr);
            return (Serializable) json.get("sessionId");
        }catch (Exception e)
        {
            log.error("失敗的秘聞:{}",token);
            e.printStackTrace();
            return null;
        }

    }
}
