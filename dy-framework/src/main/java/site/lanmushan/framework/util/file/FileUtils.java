package site.lanmushan.framework.util.file;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件操作工具类
 * @author Administrator
 */
public class FileUtils {
     private static final Logger log = LoggerFactory.getLogger(FileUtils.class);
    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName)
    {
        if(StringUtils.isEmpty(fileName))
        {
            log.error("文件名为空 fileName={}",fileName);
            return null;
        }
        return fileName.substring(fileName.lastIndexOf('.'));
    }

}
