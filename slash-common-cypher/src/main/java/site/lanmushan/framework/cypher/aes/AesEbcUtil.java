package site.lanmushan.framework.cypher.aes;


import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.pqc.jcajce.provider.BouncyCastlePQCProvider;
import site.lanmushan.framework.cypher.base64.Base64Util;
import site.lanmushan.framework.cypher.hex.HexUtil;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AesEbcUtil {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法
    private static final String password = "23slfjlajwaljosj78/123";
    static {
        Security.addProvider(new BouncyCastlePQCProvider());
    }
    public static String encryptBase64(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));// 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);// 加密

            return Base64Util.encodeToString(result);//通过Base64转码返回
        } catch (Exception ex) {
            Logger.getLogger(AesEbcUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static String encryptToHex(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));// 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);// 加密

            return HexUtil.byteToHex(result);//通过Base64转码返回
        } catch (Exception ex) {
            Logger.getLogger(AesEbcUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * AES 解密操作
     *
     * @param content
     * @param password
     * @return
     */
    public static String decryptByBase64(String content, String password) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));

            //执行操作
            byte[] result = cipher.doFinal(Base64Util.decodeToByte(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
            Logger.getLogger(AesEbcUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    public static String decryptByHex(String content, String password) {

        try {
            if(StringUtils.isEmpty(content))
            {
                throw new NullPointerException("解密内容不能为空");
            }
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));

            //执行操作
            byte[] result = cipher.doFinal(HexUtil.hexToByte(content));

            return  new String(result,StandardCharsets.UTF_8);
        } catch (Exception ex) {
            Logger.getLogger(AesEbcUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {

        try {
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);// 创建AES的Key生产者
// //防止linux下 随机生成key
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes(StandardCharsets.UTF_8));

            //AES 要求密钥长度为 128
            kgen.init(128, random);
            //生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AesEbcUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void main(String[] args) {
        String s = "hello,您好";

        System.out.println("s:" + s);

        String s1 = AesEbcUtil.encryptToHex(s, "sfT(!aslhlTf45.4765");
        System.out.println("s1:" + s1);

        System.out.println("s2:" + AesEbcUtil.decryptByHex(s1, "sfT(!aslhlTf45.4765"));


    }

}

