package site.lanmushan.query.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.util.ReflectionUtil;
import site.lanmushan.query.annotations.RequestQueryInfo;
import site.lanmushan.query.util.ServletUtil;
import site.lanmushan.query.util.StringCommonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @RequestQueryInfo 注解数据注入功能实现
 * @Author dy
 * @Date 2020/6/7 13:59
 * @Version 1.0
 */
@Component
@Slf4j
public class RequestQueryInfoHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    /**
     * 判断是否属于该处理器处理，返回true的情况会调用resolveArgument
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(RequestQueryInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Class<?> clazz = methodParameter.getParameterType();
        if(!clazz.equals(QueryInfo.class))
        {
            return clazz.newInstance();
        }
        Object obj=clazz.newInstance();
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        Map<String,Object> map= ServletUtil.getDataFromRequest(request);
        if(map==null||map.size()==0)
        {
            return obj;
        }
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        List<QueryInfo.QueryParam> params=new ArrayList<>();
        while (it.hasNext())
        {
            Map.Entry<String, Object> entry = it.next();
            if(StringUtils.isEmpty(entry.getKey())||entry.getValue()==null||StringUtils.isEmpty(entry.getValue().toString()))
            {
                continue;
            }
            String key=entry.getKey();
            Object value=entry.getValue();
            if(specialField(obj,key,value))
            {
               continue;
            }
            QueryInfo.QueryParam queryParam=new QueryInfo.QueryParam();
            String[] keys = key.split("-");
            String fiexedName="";
            String operateCode="";

            int index=0;
            if(keys.length>=1)
            {
                 fiexedName=getFieldName(keys[0]);
            }
            if(keys.length>=2)
            {
                operateCode=getOperate(keys[1]);
            }else {
                operateCode="=";
            }
            if(keys.length>=3)
            {
                index= Integer.parseInt(keys[2]);
            }
            queryParam.setKey(fiexedName);
            queryParam.setOperate(operateCode);
            queryParam.setValue(value);
            queryParam.setIndex(index);
            params.add(queryParam);
        }
        Collections.sort(params, new Comparator<QueryInfo.QueryParam>() {
            @Override
            public int compare(QueryInfo.QueryParam o1, QueryInfo.QueryParam o2) {
                return -(o1.getIndex()-o2.getIndex());
            }
        });
        ReflectionUtil.setFieldValue(obj, "map", map);
        ReflectionUtil.setFieldValue(obj,"parList",params);
        return obj;
    }
    /**
     * 获取字段名
     * @param key
     * @return
     */
    private String getFieldName(String key)
    {
        if(key.indexOf("_")!=-1)
        {
            throw new OperateException("参数错误");
        }
        return StringCommonUtil.camelToUnderline(key,'_');
    }
    /**
     * 获取操作符
     * @param key
     * @return
     */
    private String getOperate(String key)
    {
        if(key.length()<0)
        {
            return "=";
        }
        String operate=key;
        operate=operate.toLowerCase();
        switch (operate)
        {
            case "like":{return "like";}
            case "in":{return "in";}
            case "eq":{return "=";}
            case "neq":{return "!=";}
            case "gt":{return ">";}
            case "lt":{return "<";}
            case "gte":{return ">=";}
            case "lte":{return "<=";}
            default:{return "=";}
        }
    }

    /**
     * 进行特殊处理
     * @param obj
     * @param key
     * @param value
     * @return
     */
    private boolean specialField(Object obj,String key,Object value) {
           try {
               if(key.equals("searchKey"))
               {
                   ReflectionUtil.setFieldValue(obj, key, value.toString() + "%");
               }else {
                   ReflectionUtil.setFieldValue(obj,key,value);
               }
               return true;
           }catch (Exception e){
               return false;
           }



    }
}
