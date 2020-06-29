package com.lanmushan.cypher.aes;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author dy
 * @Date 2020/6/20 23:22
 * @Version 1.0
 */
@Slf4j
public class AESUtil {
    public static BASE64Encoder base64Encoder = new BASE64Encoder();
    public static BASE64Decoder base64Decoder = new BASE64Decoder();

    public enum AES_ALGORITHM {
        /**
         * 默认算法
         */
        ECB_PKCS5Padding("AES/ECB/PKCS5Padding"),
        CBC_PKCS5Padding("AES/CBC/PKCS5Padding");
        public String value;

        AES_ALGORITHM(String value) {
            this.value = value;
        }
    }

    /**
     * 指定密码器
     */
    private static final String KEY_ALGORITHM = "AES";

    public static String encryptString(String content, String password) {
        return encryptString(AES_ALGORITHM.ECB_PKCS5Padding, content, password);
    }

    /**
     * @param algorithm 加密算法
     * @param content   加密内容
     * @param password  密码
     * @return
     */
    public static String encryptString(AES_ALGORITHM algorithm, String content, String password) {
        byte[] result = encryptByte(algorithm, content, password);
        return base64Encoder.encode(result);
    }

    public static byte[] encryptByte(AES_ALGORITHM algorithm, String content, String password) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(algorithm.value);

            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String decryptString(String content, String password) {
        return decryptString(AES_ALGORITHM.ECB_PKCS5Padding, content, password);
    }

    public static String decryptString(AES_ALGORITHM algorithm, String content, String password) {
        byte[] result = decryptByte(algorithm, content, password);
        return new String(result, StandardCharsets.UTF_8);
    }

    public static byte[] decryptByte(AES_ALGORITHM algorithm, String content, String password) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(algorithm.value);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));

            //执行操作
            byte[] result = cipher.doFinal(base64Decoder.decodeBuffer(content));
            return result;
            // return new String(result, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }


    /**
     * 生成加密秘钥
     * {@link AESUtil}
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            kg.init(128, new SecureRandom(password.getBytes()));
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void main(String[] args) {
        String content = "sdaflsdjflasj";
        String passowrd = "asdflasjlasjlj";
        String temp;

        temp = AESUtil.encryptString(content, passowrd);
        log.info("密文:{}", temp);
        temp = AESUtil.decryptString(temp, passowrd);
        log.info("明文:{}", temp);
    }
}
