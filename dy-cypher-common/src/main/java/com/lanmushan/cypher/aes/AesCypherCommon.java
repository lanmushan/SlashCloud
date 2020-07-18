package com.lanmushan.cypher.aes;

import com.lanmushan.cypher.constant.EncrypModeConst;
import com.lanmushan.cypher.constant.EncrypPadding;
import com.lanmushan.cypher.support.AbstractCypherCommon;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;



/**
 * @Author daiyu
 * @Date 2020/7/16 20:15
 * @Version 1.0
 */
@Slf4j
public class AesCypherCommon extends AbstractCypherCommon {
    protected String algorithm = "AES";
    private String mode= EncrypModeConst.CBC;
    public String padding=EncrypPadding.PKCS5_PADDING;
    private int length = 128;
    public String password = "1234567890123456";

    @Override
    public byte[] encrypt(byte[] bytes) {
        try {
            // 创建密码器
            Cipher cipher = getCipher();
            IvParameterSpec iv = new IvParameterSpec(password.getBytes());
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(),iv);
            // 加密
            byte[] result = cipher.doFinal(bytes);
            return result;
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(),ex);
        }
        return null;
    }


    @Override
    public byte[] decrypt(byte[] bytes) {
        try {
            //实例化
            Cipher cipher =getCipher();
            IvParameterSpec iv = new IvParameterSpec(password.getBytes());
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(),iv);
            //执行操作
            byte[] result = cipher.doFinal(bytes);
            return result;
        } catch (Exception ex) {
           log.error(ex.getLocalizedMessage(),ex);
        }
        return null;
    }

    /**
     * 获取SecretKeySpec
     *
     * @return
     */
    private SecretKeySpec getSecretKey() {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(algorithm);
            kg.init(length, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kg.generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), algorithm);
        } catch (NoSuchAlgorithmException ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return null;
    }
    public Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        String transform=algorithm+"/"+mode+"/"+padding;
        if(!EncrypPadding.PKCS5_PADDING.equals(padding)&&EncrypPadding.PKCS7_PADDING.equals(padding))
        {
            transform=this.algorithm+"/"+mode+"/"+EncrypPadding.No_PADDING;
        }
        Cipher cipher = Cipher.getInstance(transform);
        return cipher;
    }
    public static void main(String args[]) {
        String str = "长度是一维空间的度量，为点到点的距离。通常在量度二维空间中量度线段边长时，称呼长度数值较大的为长";
        AbstractCypherCommon cypherCommon = new AesCypherCommon();
        log.info("原文:"+str);
        String base64 = cypherCommon.encryptToBase64(str);
        log.info("base64:"+base64);
        log.info("明文:"+cypherCommon.decryptByBase64ToString(base64));

        String hex = cypherCommon.encryptToHex(str);
        log.info("Hex:"+hex);
        log.info("明文:"+cypherCommon.decryptByHexToString(hex));
    }
}
