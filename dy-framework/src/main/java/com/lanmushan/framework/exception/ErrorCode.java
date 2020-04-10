package com.lanmushan.framework.exception;

/**
 * 状态编号定义
 */
public class ErrorCode {
    /**
     * 操作成功
     */
     public static Integer SUCCESS=1;
    /**
     * 未知错误
     */
    public static Integer UNKNOWN_ERROR=0;
    /**
     * 服务暂时不可用
     */
    public static Integer SERVICE_TEMPORARILY_UNAVAILABLE=2;
    /**
     * 未知的方法
     */
    public static Integer UNSUPPORTED_OPENAPI_METHOD=3;
    /**
     * 接口调用次数已达到设定的上限
     */
    public static Integer OPEN_API_REQUEST_REACHED=4;
    /**
     * 请求来自未经授权的IP地址
     */
    public static Integer UNAUTHORIZED_IP_ADDRESS=5;
    /**
     * 无权限访问该用户数据
     */
    private static Integer NO_PERMISSION_ACCESS_DATA=6;
    /**
     * 请求重定向
     */
    public static Integer REDIRECT=-1;


}
