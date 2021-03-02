package site.lanmushan.framework.util.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 将Json格式转换为URL编码格式
 *
 * @author dy
 */
public class JsonUrlUtil {

    public static Map<String, String> toNameValue(Object data) {
        Map<String, String> resultMap = new HashMap<>(8);
        if (null == data) {
            return null;
        }
        if (data instanceof JSONObject) {
            ((JSONObject) data).forEach((key, value) -> {
                if (value instanceof JSONObject) {
                    toNameValue(value).forEach((subKey, subValue) -> {
                        resultMap.put(key + "." + subKey, subValue);
                    });
                } else if (value instanceof JSONArray) {
                    toNameValue(value).forEach((subKey, subValue) -> {
                        resultMap.put(key + subKey, subValue);
                    });
                } else if (value != null) {
                    resultMap.put(key, String.valueOf(value));
                }
            });
        } else if (data instanceof JSONArray) {
            JSONArray array = ((JSONArray) data);
            for (int i = 0; i < array.size(); i++) {
                Object it = array.get(i);
                if (it instanceof String) {
                    resultMap.put("[" + i + "]", String.valueOf(it));
                } else {
                    Iterator<Map.Entry<String, String>> entries = toNameValue(it).entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry<String, String> entry = entries.next();
                        String key = entry.getKey();
                        String value = entry.getValue();
                        resultMap.put("[" + i + "]" + "." + key, value);
                    }
                }
            }
        } else if (data instanceof List) {
            JSONArray jsonArray = (JSONArray) JSONArray.toJSON(data);
            toNameValue(jsonArray);
        } else if (data instanceof Object) {
            JSONObject json = (JSONObject) JSONObject.toJSON(data);
            resultMap.putAll(toNameValue(json));
        }
        return resultMap;
    }

    public static String parseNameValue(Object data) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> map = toNameValue(data);
        map.forEach((key, value) -> {
            sb.append(key);
            sb.append("=");
            sb.append(value);
            sb.append("&");
        });
        return sb.toString().substring(0, sb.length() - 1);
    }
}
