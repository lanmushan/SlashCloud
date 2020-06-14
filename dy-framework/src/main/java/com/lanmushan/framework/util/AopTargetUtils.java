package com.lanmushan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperProxy;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import tk.mybatis.spring.mapper.MapperFactoryBean;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * @Author dy
 * @Date 2020/6/13 20:03
 * @Version 1.0
 */
@Slf4j
public class AopTargetUtils {

    /**
     * 获取 目标对象
     * @param proxy 代理对象
     * @return
     * @throws Exception
     */
    public static Object getTarget(Object proxy) throws Exception {

        if(!AopUtils.isAopProxy(proxy)) {
            return proxy;//不是代理对象
        }
        if(AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTargetObject(proxy);
        } else  if (AopUtils.isCglibProxy(proxy)){ //cglib
            return getCglibProxyTargetObject(proxy);
        }else {
            return getTarget(proxy);
        }



    }
    private static Boolean isMapperProxy(Object proxy)  {
        try {
         return Proxy.isProxyClass(proxy.getClass());

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    return null;
    }
    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
        log.info("get target object ["+target.getClass().getName()+"] from Cglib reflect ,from proxy:"+proxy.getClass().getName());
        return target;
    }

    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
        log.info("get target object ["+target.getClass().getName()+"] from Jdk reflect ,from proxy:"+proxy.getClass().getName());
        return target;
    }

}
