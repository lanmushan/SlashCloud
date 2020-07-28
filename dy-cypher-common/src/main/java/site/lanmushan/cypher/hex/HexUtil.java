package site.lanmushan.cypher.hex;

import lombok.extern.slf4j.Slf4j;

/**
 * @author  dy
 * @Date 2020/7/13 22:43
 * @Version 1.0
 */
@Slf4j
public class HexUtil {

    /**
     * 字节流转16进制
     * @param
     * @return  转换后的Hex字符串
     */
    public static String byteToHex(byte[] bytes){
        String temp = "";
        StringBuilder sb=new StringBuilder(temp) ;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xff);
            if (temp.length() == 1) {
                sb.append("0"+temp);
            } else {
                sb.append(temp);
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 16进制转换为Byte
     * @param hex
     * @return
     * @throws IllegalArgumentException
     */
    public static byte[] hexToByte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] bytes = new byte[hex.length() / 2];
        for(int i=0,j=0;i<arr.length;i+=2,j++){
           String str= String.valueOf(arr,i,2);
            bytes[j]= (byte) Integer.parseInt(str,16);
        }
        return bytes;
    }

    public static void main(String args[]){
            String str="测试数据测试";
            String hex= HexUtil.byteToHex(str.getBytes());
            log.info("16进制数据{}",hex);
             log.info("还原的数据{}",str);
    }
}
