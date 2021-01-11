package site.lanmushan.framework.cypher.base64;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;

/**
 * @Author dy
 * @Date 2020/6/21 12:35
 * @Version 1.0
 */
@Slf4j
public class Base64Util {
    public static BASE64Encoder base64Encoder = new BASE64Encoder();
    public static BASE64Decoder base64Decoder = new BASE64Decoder();


    public static String encodeToString(String content) {
        return Base64Util.encodeToString(content.getBytes(StandardCharsets.UTF_8));
    }
    public static String encodeToString(byte[] bytes) {
        return base64Encoder.encode(bytes);
    }

    public static String decodeToString(String content) {
        byte[] bytes = Base64Util.decodeToByte(content);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static byte[] decodeToByte(String content) {
        try {
            return base64Decoder.decodeBuffer(content);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }

    public static void main(String[] args) {
        String content = "asdfasdlfjasljas";
        String temp = Base64Util.encodeToString(content);
        log.info("编码:{}", temp);
        log.info("明文:{}", Base64Util.decodeToString(temp));
    }
}
