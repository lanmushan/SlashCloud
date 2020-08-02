package site.lanmushan.authorization.util;

import com.alibaba.fastjson.JSONObject;
import site.lanmushan.authorization.CurrentUser;
import site.lanmushan.cypher.aes.AesEbcUtil;
import site.lanmushan.cypher.md5.MD5Util;

/**
 * 令牌生成器
 */
public class TokenUtil {
    public static String createToekn(Object obj,String secretKey){
        String str= JSONObject.toJSONString(obj);
          return   AesEbcUtil.encryptToHex(str,secretKey);
    }
    public static String resolveToken(String token,String secretKey){
        return   AesEbcUtil.decryptByHex(token,secretKey);
    }
}
