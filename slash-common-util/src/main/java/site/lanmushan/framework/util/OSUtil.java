package site.lanmushan.framework.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @author Administrator
 */
@Slf4j
public class OSUtil {
    /**
     * 转换为当前系统路径
     *
     * @param path
     * @return
     */
    public static String convertToCurrentOsPath(String path) {
        // log.info("是否为Linux{}",OSUtil.isOSLinux());
        //log.info("转换路径{}",path);
        String fs = System.getProperties().getProperty("file.separator");
        if (OSUtil.isOSLinux()) {
            return path.replaceAll("\\\\", fs);
        } else {
            return path.replaceAll("/", "\\" + fs);
        }
    }

    public static boolean isOSLinux() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("win") > -1) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {

    }
}
