package com.lanmushan.framework.util.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 本地外部资源文件操作类
 * @Author dy
 * @Date 2020/4/24 22:26
 * @Version 1.0
 */
public class LocalResourceUtils {
    private static final String LOCAL_RESOURCE_PATH="resource";
    private static final String RESOURCE_PUBLIC_PATH="resource/public";
    private static final String RESOURCE_PRIVATE_PATH="resource/private";
    public static String getLocalResourceFilePath(){
        File currentFile=new File("");
        return  currentFile.getAbsolutePath()+File.separatorChar+LOCAL_RESOURCE_PATH;
    }
    public static File getLocalResourceFile(){
        File file=new File(getLocalResourceFilePath());
        if(!file.exists())
        {
            file.mkdirs();
        }
        return file;
    }
    public static String getLocalResourcePublicPath(){
        File currentFile=new File("");
        return  currentFile.getAbsolutePath()+File.separatorChar+RESOURCE_PUBLIC_PATH;
    }
    public static File getLocalResourcePublicFile(){
        File file=new File(getLocalResourcePublicPath());
        if(!file.exists())
        {
            file.mkdirs();
        }
        return file;
    }
    public static String getLocalResourcePrivatePath(){
        File currentFile=new File("");
        return  currentFile.getAbsolutePath()+File.separatorChar+RESOURCE_PRIVATE_PATH;
    }
    public static File getLocalResourcePrivateFile(){
        File file=new File(getLocalResourcePrivatePath());
        if(!file.exists())
        {
            file.mkdirs();
        }
        return file;
    }
    public static File getLocalResourcePrivateFile(String path){
        File file=new File(getLocalResourcePrivatePath()+File.separatorChar+path);
        if(!file.exists())
        {
            file.mkdirs();
        }
        return file;
    }
    public static File getLocalResourcePublicFile(String path){
        File file=new File(getLocalResourcePublicPath()+File.separatorChar+path);
        if(!file.exists())
        {
            file.mkdirs();
        }
        return file;
    }
    public static void main(String[] args) {
        FileUtils.getSuffix("111.11");
        System.out.println(LocalResourceUtils.getLocalResourceFilePath());
        System.out.println(LocalResourceUtils.getLocalResourceFile());
        System.out.println(LocalResourceUtils.getLocalResourcePublicPath());
        System.out.println(LocalResourceUtils.getLocalResourcePublicFile());
        System.out.println(LocalResourceUtils.getLocalResourcePrivateFile());

    }
}
