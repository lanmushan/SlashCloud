package com.lanmushan.cypher.support;

import com.lanmushan.cypher.base64.Base64Util;
import com.lanmushan.cypher.hex.HexUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Author dy
 * @Date 2020/7/13 22:41
 * @Version 1.0
 */
public abstract class AbstractCypherCommon {
    protected Charset charset = StandardCharsets.UTF_8;

    /**
     * 加密并转换为Base64
     * @param content
     * @return
     */
    public String encryptToBase64(String content) {
        byte[] bytes = encrypt(content.getBytes(charset));
        return Base64Util.encodeToString(bytes);
    }

    /**
     * Base64解密并转换为String
     * @param content
     * @return
     */
    public String decryptByBase64ToString(String content) {
        byte[] bytes=  Base64Util.decodeToByte(content);
        return new String(decrypt(bytes),charset);
    }

    /**
     * Base64解密并转换为byte
     * @param content
     * @return
     */
    public byte[] decryptByBase64ToByte(String content) {
        byte[] bytes=  Base64Util.decodeToByte(content);
        return decrypt(bytes);
    }

    /**
     * 加密并转换为16进制
     * @param content
     * @return
     */
    public String encryptToHex(String content) {
        byte[] bytes = encrypt(content.getBytes(charset));
        return HexUtil.byteToHex(bytes);
    }

    /**
     * 解密并转换为String
     * @param content
     * @return
     */
    public String decryptByHexToString(String content) {
        byte[] bytes=HexUtil.hexToByte(content);
        return new String(decrypt(bytes),charset);
    }

    /**
     * 解密并转换为byte
     * @param content
     * @return
     */
    public byte[] decryptByHexToByte(String content) {
           byte[] bytes=HexUtil.hexToByte(content);
           return decrypt(bytes);
    }

    public abstract byte[] encrypt(byte[] bytes);

    public abstract byte[] decrypt(byte[] bytes);
}
