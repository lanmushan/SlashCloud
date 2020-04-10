package com.lanmushan.framework.util.file;

/**
 * 文件操作工具类
 */
public class FileUtils {
    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName)
    {
        return fileName.substring(fileName.lastIndexOf('.'));
    }

}
