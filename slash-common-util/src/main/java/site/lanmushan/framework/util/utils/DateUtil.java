package site.lanmushan.framework.util.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期操作相关工具
 *
 * @author Administrator
 */
public class DateUtil {
    public static Date now() {
        return new Date();
    }

    public static String fomat_yyyyMMddHHmmss(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    public static String fomat_yyyyMMddHHmmss() {
        return fomat_yyyyMMddHHmmss(DateUtil.now());
    }
}
