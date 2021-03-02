package site.lanmushan.framework.file;


import java.util.List;

public interface LocalResourceService {
    String getRootPath();

    String getWebAppPath();

    String getTemplatesPath();

    String getConfigPath();

    List<VoFile> getRootFiles(String path);

    List<VoFile> getFiles(String path);

    List<VoFile> getTemplatesFiles();

    /**
     * 读取文件
     *
     * @param path
     * @return
     */
    byte[] readFile(String path);

    /**
     * 删除文件
     *
     * @param path
     * @return
     */
    boolean deleteFile(String path);

    /**
     * 判断文件是否存在
     *
     * @param path
     * @return
     */
    boolean webExists(String path);

    /**
     * 判断文件是否存在
     *
     * @param path
     * @return
     */
    byte[] webRead(String path);
}
