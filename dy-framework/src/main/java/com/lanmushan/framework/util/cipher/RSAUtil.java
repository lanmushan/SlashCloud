package com.lanmushan.framework.util.cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密
 */
public class RSAUtil {
    public static String data = "12345";
    public static BASE64Encoder base64Encoder = new BASE64Encoder();
    public static BASE64Decoder base64Decoder = new BASE64Decoder();
    // 公钥
    private static String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/OpVr+aQu6B3stSUgsLcZWpaxatset8zTqat1FF543hoECcTnRqDXKwfX09J+RLCc/1fbITt0s4wUUwJNU7lKJSTGZp5/xHcEiFJjTa+XY6pQHQKvvZjAQMkyzC3H5tmaNTapKYJOAWw7u1dxcRNFdD3k5E+EiqSnlo30u7SLCwIDAQAB";
    // 私钥
    private static String privateKeyStr = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL86lWv5pC7oHey1JSCwtxlalrFq2x63zNOpq3UUXnjeGgQJxOdGoNcrB9fT0n5EsJz/V9shO3SzjBRTAk1TuUolJMZmnn/EdwSIUmNNr5djqlAdAq+9mMBAyTLMLcfm2Zo1Nqkpgk4BbDu7V3FxE0V0PeTkT4SKpKeWjfS7tIsLAgMBAAECgYBicjt4geV3TIITWVJK2Q76G3vWzIcP8lmdYgzl0l2sZdMI3yqiUeb9vqZkAyWrYZt2x7GoGxyrwL9Nu0pFGuQZFaZIrHRj6LoNq/dgGUpN5zviXUDq2RrhhP7dW4Zc2UbbZqtTzn4jgv8/dviT+LACBmbavojjbb6YZHO/YDml2QJBAPWWu7SkyqfHSDOBBYWyI0GON2ApqTOIsENpQ572IvjNzT8TcXsNRr1hy4o5JfJN4KutBSsJkxAv3+nCc7pvRo0CQQDHVefkgjyuCyQjTtm8WPeIP7Ny8Rul44SmoyaSOANiPufsjIAPvxtNwyvkyUKtI7AMx6XrAWltRMWWiByVH533AkBp87fTfWz46V7a6YTqYyoWtDZrxE19MDFrQ9SqleIMmS09UzQYNGgaeECJx5H5cWPGbQTXxm+uAhmGDiBDhJJZAkEAu84SR1b1OL1CdQmrVyszPGlX9ul3NRphNmbsxkKD3aKK/HF7jlptrRw/VLTSXzIKgl/v0LRp0gtDZgojc9RwDQJBAJ2d0E9huqG9yP0bA9q0lIFwqJogLnoRvQCkNW6hATUrA5b7lrZYniPbwRfSALW2jgweTeTaeouPBHPWbVz/ws8=";
    private static RSAUtil ourInstance = new RSAUtil();


    // 生成密钥对
    private void generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator;
        keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 获取公钥，并以base64格式打印出来
        PublicKey publicKey = keyPair.getPublic();
        publicKeyStr = base64Encoder.encode(publicKey.getEncoded());
        // 获取私钥，并以base64格式打印出来
        PrivateKey privateKey = keyPair.getPrivate();
        privateKeyStr = base64Encoder.encode(privateKey.getEncoded());
    }

    // 将base64编码后的公钥字符串转成PublicKey实例
    private static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = base64Decoder.decodeBuffer(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    // 将base64编码后的私钥字符串转成PrivateKey实例
    private static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = base64Decoder.decodeBuffer(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    // 公钥加密
    public static String encryptByPublicKey(String content) throws Exception {
        // 获取公钥
        PublicKey publicKey = getPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherText = cipher.doFinal(content.getBytes());
        String cipherStr = base64Encoder.encode(cipherText);
        return cipherStr;
    }

    // 私钥加密
    public static String encryptByPrivateKey(String content) throws Exception {
        // 获取私钥
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] cipherText = cipher.doFinal(content.getBytes());
        String cipherStr = base64Encoder.encode(cipherText);
        return cipherStr;
    }

    // 私钥解密
    public static String decryptByPrivateKey(String content) throws Exception {
        // 获取私钥
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] cipherText = base64Decoder.decodeBuffer(content);
        byte[] decryptText = cipher.doFinal(cipherText);
        return new String(decryptText);
    }

    // 公钥解密
    public static String decryptByPublicKey(String content) throws Exception {
        // 获取公钥
        PublicKey publicKey = getPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] cipherText = base64Decoder.decodeBuffer(content);
        byte[] decryptText = cipher.doFinal(cipherText);
        return new String(decryptText);
    }


    public static void main(String[] args) throws Exception {
        System.out.println("初始数据："+data);
        // 公钥加密
        String encryptedBytes = encryptByPublicKey(data);
        System.out.println("公钥加密后：" + encryptedBytes);
        // 私钥解密
        String decryptedBytes = decryptByPrivateKey(encryptedBytes);
        System.out.println("私钥解密后：" + decryptedBytes);
        // 私钥加密
        String encryptedBytes2 = encryptByPrivateKey(data);
        System.out.println("私钥加密后：" + encryptedBytes2);
        // 公钥解密
        String decryptedBytes2 = decryptByPublicKey(encryptedBytes2);
        System.out.println("公钥解密后：" + decryptedBytes2);
    }

}
