package com.lanmushan.framework.exception;

/**
 * 自定义异常
 * @author Administrator
 */
public class OperateException extends RuntimeException {
    private Integer code;
    public OperateException(String message) {
        super(message);
    }
    public OperateException(String message, Integer code) {
        super(message);
        if(code!=0)
        {
            this.code=code;
        }else{
            this.code=1;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
