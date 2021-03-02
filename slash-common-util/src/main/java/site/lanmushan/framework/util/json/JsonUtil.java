package site.lanmushan.framework.util.json;

import com.alibaba.fastjson.JSONObject;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Administrator
 */
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
        // 表示这样也可以从类路径下获取文件，只不过返回一个InputStream 对象
        InputStream in = JsonUtil.class.getClassLoader().getResourceAsStream(filePath);

        // ClassPathResource resource = new ClassPathResource(filePath);
        //  InputStream in=  resource.getInputStream();
        StringBuilder sb = new StringBuilder();
        byte[] bytes = new byte[1024];
        int len = -1;
        while ((len = in.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
        }
        return sb.toString();
    }

}