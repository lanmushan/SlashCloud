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

    public static String createMD5AndSalt(String plainText,String salt,int n)
    {
        for (int i=0; i<n;i++)
        {
            plainText=createMD532(plainText+salt);
             salt=new StringBuilder(salt+i).reverse().toString();
             plainText=new StringBuilder(plainText+(n-i)).reverse().toString();
        }
        return  createMD532(salt+plainText);

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
        String text = "aa";
        System.out.println(MD5Util.createMD5AndSalt(text,"asdf",3));
        System.out.println(MD5Util.createMD5AndSalt(text,"a",3));
    }
}
