package com.lanmushan.framework.dto;

import com.lanmushan.framework.util.StringCommonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author dy
 * @Date 2020/6/7 19:48
 * @Version 1.0
 */
public class DHashMap extends HashMap {
    @Override
    public Object put(Object key, Object value) {
       String camelKey= StringCommonUtil.underlineToCamel(key.toString(),'_');
       if(value instanceof Boolean)
       {
           return super.put(camelKey, (Boolean) value?1:0);
       }
        return super.put(camelKey, value);
    }

    @Override
    public void putAll(Map m) {
        super.putAll(m);
    }
}
