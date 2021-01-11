package site.lanmushan.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author dy
 * @Date 2020/6/10 21:08
 * @Version 1.0
 */
public class ConstantResource {
    public static ConstantResource bulid(String configPath)
    {
        return   new ConstantResource(configPath);
    }
    private  Map<String, String> map = new HashMap<>();
    public ConstantResource(String configPath) {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(configPath);
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(resourceAsStream,"utf-8");
            Properties properties = new Properties();
            properties.load(inputStreamReader);
            Enumeration enumeration = properties.propertyNames();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                String value = properties.getProperty(key);
                map.put(key, value);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    static {

    }
    public  String getConstant(String key){
     return   map.get(key);
    }
}
