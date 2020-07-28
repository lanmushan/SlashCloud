package site.lanmushan.cypher.md5;

import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author dy
 * @Date 2020/6/21 13:51
 * @Version 1.0
 */
public class MD5Util {
    private static final String KEY_ALGORITHM = "MD5";

    public static final String createMD564(String plainText) {
        try {
            // 确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            // 加密后的字符串
            String newstr = base64en.encode(md5.digest(plainText.getBytes(StandardCharsets.UTF_8)));
            return newstr;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 创建32位md5加密
     *
     * @param plainText
     * @return
     */
    public static String createMD532(String plainText) {
        String re_md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuffer buf = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    public static void main(String[] args) {
        String text = "asdfassafsdzzzzzzzzzzzzzzzzzzzasdfassafsdzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzasdffffffffffffffffffffffffffffffffffffffffffffffffffffffffffasasdfassafsdzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzasdfffffffffffffffffffffffffffffffffffffafffffffffffffffffffffasasdfassafsdzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzasdffffffffffffffffffffffffffffffffffffffffffffffffffffffffffaszzzzzzzzzzzzasdffffffffffffffffffffffffffffffffffffffffffffffffffffffffffas";
        System.out.println(MD5Util.createMD532(text));
        System.out.println(MD5Util.createMD564(text));
    }
}
