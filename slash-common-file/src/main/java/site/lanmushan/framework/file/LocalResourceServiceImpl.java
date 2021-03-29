package site.lanmushan.framework.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import site.lanmushan.framework.exception.OperateException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Slf4j
public class LocalResourceServiceImpl implements LocalResourceService {
    private String rootPath;
    private static final String templatesPath = "templates";
    private static final String webAppPath = "webApp";
    private static final String configPath = "config";

    public LocalResourceServiceImpl(String rootPath) throws IOException {
        File file = new File(rootPath);
        this.rootPath = file.getCanonicalPath();
        log.info("外部资源文件目录{}", rootPath);
        File templatesFile = new File(this.getTemplatesPath());
        if (!templatesFile.exists()) {
            log.info("创建模板文件夹{}", templatesFile.getCanonicalPath());
            templatesFile.mkdirs();
        }
        File webAppFile = new File(this.getWebAppPath());
        if (!webAppFile.exists()) {
            log.info("创建WebApp文件夹{}", webAppFile.getCanonicalPath());

            webAppFile.mkdirs();
        }
    }

    @Override
    public String getRootPath() {
        return this.rootPath;
    }

    @Override
    public String getWebAppPath() {
        return rootPath + File.separator + webAppPath;
    }

    @Override
    public String getTemplatesPath() {
        return rootPath + File.separator + templatesPath;

    }

    @Override
    public String getConfigPath() {
        return rootPath + File.separator + configPath;
    }

    @Override
    public List<VoFile> getRootFiles(String path) {
        if (StringUtils.isEmpty(path)) {
            path = this.getRootPath();
        } else {
            path = this.getRootPath().concat(path);
        }
        return getFiles(path);
    }

    @Override
    public List<VoFile> getFiles(String path) {
        List<VoFile> resultList = new ArrayList<>();

        try {
            File file = new File(path);
            File[] tempList = file.listFiles();
            for (int i = 0; i < tempList.length; i++) {
                String fileName = tempList[i].getName();
                VoFile voFile = new VoFile();
                voFile.setFileName(fileName);
                String filePath = tempList[i].getCanonicalPath();
                filePath = filePath.replace(getRootPath(), "");
                voFile.setFilePath(filePath);
                if (tempList[i].isFile()) {
                    voFile.setFileSize(tempList[i].length());
                    voFile.setFileType(fileName.substring(fileName.lastIndexOf(".")));
                } else if (tempList[i].isDirectory()) {
                    voFile.setFileType("directory");
                    voFile.setFileSize(tempList[i].length());
                }
                resultList.add(voFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<VoFile> getTemplatesFiles() {
        return getFiles(this.getTemplatesPath());
    }


    @Override
    public byte[] readFile(String path) {
        String tempFile = this.getRootPath().concat(path);
        File file = new File(tempFile);
        FileInputStream in=null;
        if (!file.exists()) {
            throw new OperateException("文件不存在" + path);
        }
        if (file.isDirectory()) {
            throw new OperateException("读取目标不是一个文件");
        } else {
            try {
                Long fileLength = file.length();
                if (fileLength == -1) {
                    return new byte[0];
                }
                byte[] resultByte = new byte[fileLength.intValue()];
                 in = new FileInputStream(file);
                in.read(resultByte);
                return resultByte;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new OperateException("文件读取失败");
            }finally {
                if(in!=null)
                {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public boolean deleteFile(String path) {
        String tempFile = this.getRootPath().concat(path);
        File file = new File(tempFile);
        if (!file.exists()) {
            throw new OperateException("文件不存在");
        }
        if (file.isDirectory()) {
            throw new OperateException("不能直接删除文件夹");
        }
        return file.delete();
    }

    @Override
    public boolean webExists(String path) {
        String tempFile = this.getWebAppPath().concat(path);
        File file = new File(tempFile);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    @Override
    public byte[] webRead(String path) {
        String tempFile = this.getWebAppPath().concat(path);
        File file = new File(tempFile);
        FileInputStream in=null;
        if (!file.exists()) {
            throw new OperateException("文件不存在" + path);
        }
        if (file.isDirectory()) {
            throw new OperateException("读取目标不是一个文件");
        } else {
            try {
                Long fileLength = file.length();
                if (fileLength == -1) {
                    return new byte[0];
                }
                byte[] resultByte = new byte[fileLength.intValue()];
                 in = new FileInputStream(file);
                in.read(resultByte);
                in.close();
                return resultByte;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new OperateException("文件读取失败");
            }finally {
              if(in!=null)
              {
                  try {
                      in.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        LocalResourceServiceImpl localResourceService = new LocalResourceServiceImpl("./resource");
        System.out.println(localResourceService.getWebAppPath());
        System.out.println(localResourceService.getTemplatesPath());
        System.out.println(localResourceService.getRootFiles(null));
    }
}
