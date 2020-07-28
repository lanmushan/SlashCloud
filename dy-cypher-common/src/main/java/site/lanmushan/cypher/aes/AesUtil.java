package site.lanmushan.cypher.aes;

import site.lanmushan.cypher.base64.Base64Util;
import site.lanmushan.cypher.hex.HexUtil;
import site.lanmushan.cypher.support.AbstractCypherCommon;
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
 * @author  dy
 * @Date 2020/7/16 20:15
 * @Version 1.0
 */
@Slf4j
public class AesUtil extends AbstractCypherCommon {
    private String algorithm = "AES";
    private  String transform=AesTransformConst.CBC_PKCS5Padding;
    private int length=128;
    private byte[] secretKeyBytes;
    private byte[] ivBytes;
    @Override
    public byte[] encrypt(byte[] bytes) {
        try {
            // 创建密码器
            Cipher cipher = getCipher();
            IvParameterSpec iv = getIvParameterSpec();
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), iv);
            // 加密
            byte[] result = cipher.doFinal(bytes);
            return result;
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return null;
    }


    @Override
    public byte[] decrypt(byte[] bytes) {
        try {
            //实例化
            Cipher cipher = getCipher();
            IvParameterSpec iv = getIvParameterSpec();
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), iv);
            //执行操作
            byte[] result = cipher.doFinal(bytes);
            return result;
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
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
            if(secretKeyBytes==null)
            {
                throw new NoSuchAlgorithmException("密钥不能为空");
            }
            kg = KeyGenerator.getInstance(algorithm);
            kg.init(length, new SecureRandom(secretKeyBytes));
            SecretKey secretKey = kg.generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), algorithm);
        } catch (NoSuchAlgorithmException ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return null;
    }

    private Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(transform);
        return cipher;
    }

    private IvParameterSpec getIvParameterSpec() throws NoSuchAlgorithmException {
        if(null==secretKeyBytes)
        {
            throw new NoSuchAlgorithmException("密钥不能为空");
        }
        ivBytes = new byte[16];
        for (int i = 0; i < 16 && i < secretKeyBytes.length; i++) {
            ivBytes[i] = secretKeyBytes[i];
        }
        return new IvParameterSpec(ivBytes);
    }
    public void setSecretKeyBas64(String secretKey){
        this.secretKeyBytes= Base64Util.decodeToByte(secretKey);
    }
    public void setSecretKeyHex(String secretKey){
        this.secretKeyBytes= HexUtil.hexToByte(secretKey);
    }
    public void setSecretKey(byte[] bytes){
        this.secretKeyBytes=bytes;
    }
    public void setSecretKey(String secretKey){
        this.secretKeyBytes=secretKey.getBytes(charset);
    }
    public static void main(String args[]) {
        String str = "长度是一维空间的度量，为点到点的距离。通常在量度二维空间中量度线段边长时，称呼长度数值较大的为长";
        AesUtil cypherCommon = new AesUtil();
        cypherCommon.setSecretKey("asdfasfas");
        log.info("原文:" + str);
        String base64 = cypherCommon.encryptToBase64(str);
        log.info("base64:" + base64);
        log.info("明文:" + cypherCommon.decryptByBase64ToString(base64));

        String hex = cypherCommon.encryptToHex(str);
        log.info("Hex:" + hex);
        log.info("明文:" + cypherCommon.decryptByHexToString(hex));
    }
}
