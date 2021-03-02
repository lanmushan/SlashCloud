package site.lanmushan.framework.authorization.util;

import com.alibaba.fastjson.JSONObject;
import site.lanmushan.framework.cypher.aes.AesEbcUtil;

/**
 * 令牌生成器
 *
 * @author Administrator
 */
public class TokenUtil {
    public static String createToken(Object obj, String secretKey) {
        String str = JSONObject.toJSONString(obj);
        return AesEbcUtil.encryptToHex(str, secretKey);
    }

    public static String resolveToken(String token, String secretKey) {
        return AesEbcUtil.decryptByHex(token, secretKey);
    }
}
