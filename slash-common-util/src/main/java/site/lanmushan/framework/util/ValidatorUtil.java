package site.lanmushan.framework.util;

import java.util.regex.Pattern;

/**
 * @program:
 * @description: 校验工具类
 * @author: xujingyang
 * @create: 2019-01-05 16:21
 **/
public class ValidatorUtil {
    /**
     * 正则表达式：验证用户名
     */
    public static final String regex_username = "^[a-zA-Z]\\w{5,20}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String regex_password = "^[a-zA-Z0-9]{6,20}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String regex_mobile = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String regex_email = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String regex_chinese = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String regex_id_card = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 正则表达式：验证URL
     */
    public static final String regex_url = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String regex_ip_addr = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    /**
     * 正则表达式：验证数字
     */
    public static final String regex_number = "^-?\\d+(\\.\\d+)?$";
    /**
     * 由数字、26个英文字母或者下划线组成的字符串
     */
    public static final String regex_number_or_letter_underline = "^\\w{3,20}$";
    /**
     * 由字母、数字组成
     */
    public static final String regex_number_or_letter = "^[A-Za-z0-9]+$";

    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(regex_username, username);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(regex_password, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(regex_mobile, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(regex_email, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(regex_chinese, chinese);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(regex_id_card, idCard);
    }

    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(regex_url, url);
    }

    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(regex_ip_addr, ipAddr);
    }

    /**
     * 是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return Pattern.matches(regex_number, str);
    }

}