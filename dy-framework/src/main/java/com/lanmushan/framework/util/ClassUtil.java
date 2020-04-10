package com.lanmushan.framework.util;

public class ClassUtil {
    public static Class getclass(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException//className是类名
    {
        // System.out.println(className);
        Class obj=Class.forName(className); //以String类型的className实例化类
        return obj;
    }
    public static void main(String args[]) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        ClassUtil.getclass("AuthUserMapper");
    }
}
