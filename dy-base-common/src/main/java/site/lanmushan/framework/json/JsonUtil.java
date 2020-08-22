package site.lanmushan.framework.json;

import com.alibaba.fastjson.JSONObject;


import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
public class JsonUtil {


    public static JSONObject loadJsonByClassPath(String filePath) {
        try {
            String str = readFileByClassPath(filePath);
            return (JSONObject) JSONObject.parse(str);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    private static String readFileByClassPath(String filePath) throws IOException {


        // 获取资源文件
        InputStream inputStream = JsonUtil.class.getClassLoader().getResourceAsStream(filePath);
        StringBuilder sb = new StringBuilder();
        byte[] bytes = new byte[1024];
        while (inputStream.read(bytes) != -1) {
            sb.append(new String(bytes, StandardCharsets.UTF_8));
        }
        return sb.toString();
    }
}