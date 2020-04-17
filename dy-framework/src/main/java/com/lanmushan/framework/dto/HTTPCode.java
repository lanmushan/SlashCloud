package com.lanmushan.framework.dto;

/**
 * 状态编号定义
 * @author dy
 */
public enum HTTPCode {
    /**
     * 操作成功的情况
     */
    OK(200,"操作成功"),
    /**
     * 操作失败的情况
     */
    NotFound(404,"请求失败"),
    /**
     * 重定向
     */
    Redirect(300,"重定向");

    private String name;
    private Integer code;

    HTTPCode() {
    }

    HTTPCode(int code, String name) {
        this.name=name;
        this.code=code;
    }

    public static void main(String[] args) {
      System.out.println(OK.code);
    }
}
