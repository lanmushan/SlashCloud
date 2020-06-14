package com.lanmushan.framework.dto;

import com.alibaba.fastjson.JSON;
import com.lanmushan.framework.util.ReflectionUtil;
import com.lanmushan.framework.util.StringCommonUtil;


import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author dy
 * @Date 2020/6/7 19:48
 * @Version 1.0
 */
public class DHashMap<T> extends HashMap {
    @Override
    public Object put(Object key, Object value) {
       String camelKey= StringCommonUtil.underlineToCamel(key.toString(),'_');
       if(value instanceof Boolean)
       {
           return super.put(camelKey, (Boolean) value?1:0);
       }
        return super.put(camelKey, value);
    }
    public static List  toEntityList(List<DHashMap> dHashMapList,Class t){
        List list=new ArrayList();
        for (DHashMap row:
                dHashMapList) {
            list.add(row.toEntity(t));
        }
        return list;
    }
    public T toEntity(Class<T> clazz){
        try {
            Object obj=  clazz.newInstance();
           this.forEach((key,value)->{
               ReflectionUtil.setFieldValue(obj, key.toString(),value);
           });
           return (T) obj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void putAll(Map m) {
        super.putAll(m);
    }
}
