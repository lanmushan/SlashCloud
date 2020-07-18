package com.lanmushan.cypher.rsa;

import com.lanmushan.cypher.base64.Base64Util;
import lombok.extern.slf4j.Slf4j;


import javax.crypto.Cipher;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Author dy
 * @Date 2020/6/21 12:27
 * @Version 1.0
 */
@Slf4j
public class RSAUtil {


    /**
     * 生成RSA密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    private static void generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator;
        keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 获取公钥，并以base64格式打印出来
        PublicKey publicKey = keyPair.getPublic();
        String publicKeyString = Base64Util.encodeToString(publicKey.getEncoded());
        // 获取私钥，并以base64格式打印出来
        PrivateKey privateKey = keyPair.getPrivate();
        String privateKeyString = Base64Util.encodeToString(privateKey.getEncoded());
        System.out.println("公钥:" + publicKeyString);
        System.out.println("私钥:" + privateKeyString);
    }

    /**
     * 将base64编码后的公钥字符串转成 PublicKey 实例
     *
     * @param publicKey
     * @return
     * @throws Exception
     */
    private static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = Base64Util.decodeToByte(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    // 将base64编码后的私钥字符串转成PrivateKey实例
    private static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = Base64Util.decodeToByte(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 加密
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String content, String publicKeyStr) throws Exception {
        // 获取公钥
        PublicKey publicKey = getPublicKey(publicKeyStr);
        return encrypt(content, publicKey);
    }

    // 私钥加密
    public static String encryptByPrivateKey(String content, String privateKeyStr) throws Exception {
        // 获取私钥
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        return encrypt(content, privateKey);
    }

    public static String encrypt(String content, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(content.getBytes());
        String cipherStr = Base64Util.encodeToString(cipherText);
        return cipherStr;
    }

    /**
     * 私钥解密
     *
     * @param content
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String content, String privateKeyStr) throws Exception {
        // 获取私钥
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] cipherText = Base64Util.decodeToByte(content);
        byte[] decryptText = cipher.doFinal(cipherText);
        return new String(decryptText);
    }

    /**
     * 公钥解密
     *
     * @param content
     * @param publicKeyStr
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String content, String publicKeyStr) throws Exception {
        // 获取公钥
        PublicKey publicKey = getPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] cipherText = Base64Util.decodeToByte(content);
        byte[] decryptText = cipher.doFinal(cipherText);
        return new String(decryptText);
    }

    private static String sign(String data, String privateKeyStr) throws Exception {
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        return RSAUtil.sign(data, privateKey);
    }

    private static String sign(String content, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(content.getBytes());
        return Base64Util.encodeToString(signature.sign());
    }

    /**
     * 验证签名
     *
     * @param content
     * @param publicKeyStr
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verifySign(String content, String publicKeyStr, String sign) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeyStr);
        return verifySign(content, publicKey, sign);
    }

    /**
     * 验证签名
     *
     * @param content
     * @param publicKey
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verifySign(String content, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(content.getBytes());
        return signature.verify(Base64Util.decodeToByte(sign));
    }

    public static void main(String[] args) throws Exception {
        // 公钥
        String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/OpVr+aQu6B3stSUgsLcZWpaxatset8zTqat1FF543hoECcTnRqDXKwfX09J+RLCc/1fbITt0s4wUUwJNU7lKJSTGZp5/xHcEiFJjTa+XY6pQHQKvvZjAQMkyzC3H5tmaNTapKYJOAWw7u1dxcRNFdD3k5E+EiqSnlo30u7SLCwIDAQAB";
        // 私钥
        String privateKeyStr = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL86lWv5pC7oHey1JSCwtxlalrFq2x63zNOpq3UUXnjeGgQJxOdGoNcrB9fT0n5EsJz/V9shO3SzjBRTAk1TuUolJMZmnn/EdwSIUmNNr5djqlAdAq+9mMBAyTLMLcfm2Zo1Nqkpgk4BbDu7V3FxE0V0PeTkT4SKpKeWjfS7tIsLAgMBAAECgYBicjt4geV3TIITWVJK2Q76G3vWzIcP8lmdYgzl0l2sZdMI3yqiUeb9vqZkAyWrYZt2x7GoGxyrwL9Nu0pFGuQZFaZIrHRj6LoNq/dgGUpN5zviXUDq2RrhhP7dW4Zc2UbbZqtTzn4jgv8/dviT+LACBmbavojjbb6YZHO/YDml2QJBAPWWu7SkyqfHSDOBBYWyI0GON2ApqTOIsENpQ572IvjNzT8TcXsNRr1hy4o5JfJN4KutBSsJkxAv3+nCc7pvRo0CQQDHVefkgjyuCyQjTtm8WPeIP7Ny8Rul44SmoyaSOANiPufsjIAPvxtNwyvkyUKtI7AMx6XrAWltRMWWiByVH533AkBp87fTfWz46V7a6YTqYyoWtDZrxE19MDFrQ9SqleIMmS09UzQYNGgaeECJx5H5cWPGbQTXxm+uAhmGDiBDhJJZAkEAu84SR1b1OL1CdQmrVyszPGlX9ul3NRphNmbsxkKD3aKK/HF7jlptrRw/VLTSXzIKgl/v0LRp0gtDZgojc9RwDQJBAJ2d0E9huqG9yP0bA9q0lIFwqJogLnoRvQCkNW6hATUrA5b7lrZYniPbwRfSALW2jgweTeTaeouPBHPWbVz/ws8=";
        String data = "12345";
        RSAUtil.generateKeyPair();
        System.out.println("初始数据：" + data);
        // 公钥加密
        String encryptedBytes = encryptByPublicKey(data, publicKeyStr);
        System.out.println("公钥加密后：" + encryptedBytes);
        // 私钥解密
        String decryptedBytes = decryptByPrivateKey(encryptedBytes, privateKeyStr);
        System.out.println("私钥解密后：" + decryptedBytes);
        // 私钥加密
        String encryptedBytes2 = encryptByPrivateKey(data, privateKeyStr);
        System.out.println("私钥加密后：" + encryptedBytes2);
        // 公钥解密
        String decryptedBytes2 = decryptByPublicKey(encryptedBytes2, publicKeyStr);
        System.out.println("公钥解密后：" + decryptedBytes2);
        String signStr = RSAUtil.sign(data, privateKeyStr);
        System.out.println("签名:" + signStr);
        System.out.println(RSAUtil.verifySign(data, publicKeyStr, signStr));
    }
}
