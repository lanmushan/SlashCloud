package site.lanmushan.framework.file;


import java.util.List;

public interface LocalResourceService {
     String getRootPath();
     String getWebAppPath();

     String getTemplatesPath();

     String getConfigPath();

     List<VoFile> getFiles(String path);

    /**
     * 读取文件
     * @param path
     * @return
     */
     byte[] readFile(String path);

    /**
     * 删除文件
     * @param path
     * @return
     */
     boolean deleteFile(String path);
}
