package site.lanmushan.authorization.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;


import lombok.extern.slf4j.Slf4j;
import site.lanmushan.authorization.constant.TokenConstants;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtil {
    /**
     * 采用HS256算法生成token
     *
     * @return
     * @throws JOSEException
     */
    public static String createTokenHS512(Object payLoad, String password) throws JOSEException, NoSuchAlgorithmException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        jwsHeader.getIncludedParams().add("111");
        JSONObject json = (JSONObject) JSON.toJSON(payLoad);
        Payload payload = new Payload(new net.minidev.json.JSONObject(json.getInnerMap()));
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        JWSSigner jwsSigner = new MACSigner(getSecretKey(password));
        jwsObject.sign(jwsSigner);
        return jwsObject.serialize();
    }

    public static byte[] getSecretKey(String secretKey) throws NoSuchAlgorithmException {
        if (null == secretKey) {
            throw new NoSuchAlgorithmException("密钥不能为空");
        }
        byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[256];
        for (int i = 0; i < 256 && i < secretKeyBytes.length; i++) {
            result[i] = secretKeyBytes[i];
        }
        return result;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     * @throws ParseException
     * @throws JOSEException
     */
    public static Map<String, Object> parseToken512(String token, String password) throws ParseException, JOSEException, NoSuchAlgorithmException {
        JWSObject jwsObject = JWSObject.parse(token);
        JWSVerifier jwsVerifier = new MACVerifier(getSecretKey(password));
        return verify(jwsObject, jwsVerifier);
    }

    /**
     * 验证token
     *
     * @param jwsObject
     * @param jwsVerifier
     * @return
     * @throws JOSEException
     */
    private static Map<String, Object> verify(JWSObject jwsObject, JWSVerifier jwsVerifier) throws JOSEException {
        Map<String, Object> resultMap = new HashMap<>();
        Payload payload = jwsObject.getPayload();
        if (jwsObject.verify(jwsVerifier)) {
            resultMap.put(TokenConstants.RESULT, TokenConstants.TOKEN_PARSE_SUCCESS);
            net.minidev.json.JSONObject jsonObject = payload.toJSONObject();
            resultMap.put(TokenConstants.DATA, jsonObject);
            if (jsonObject.containsKey(TokenConstants.EXPIRE_TIME)) {
                Long expireTime = Long.valueOf(jsonObject.get(TokenConstants.EXPIRE_TIME).toString());
                Long nowTime = System.currentTimeMillis();
                log.info("nowTime : " + nowTime);
                if (nowTime > expireTime) {
                    resultMap.clear();
                    //resultMap.put(TokenConstants.RESULT, TokenConstants.TOKEN_EXPIRED);
                }
            }
        }
        return resultMap;
    }

    public static void main(String args[]) throws JOSEException, NoSuchAlgorithmException, ParseException {
        Map<String, String> header = new HashMap<>();
        header.put("xx", "xxx");

        String token = JwtUtil.createTokenHS512(header, "123456789");
        System.out.println("结果+" + JwtUtil.parseToken512(token, "1234567189"));
        System.out.println(token);
    }
}
