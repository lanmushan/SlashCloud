package com.lanmushan.framework.exception;

import com.lanmushan.framework.constant.HTTPCode;

/**
 * 自定义异常
 * @author Administrator
 */
public class OperateException extends RuntimeException {
    public HTTPCode httpCode;

    public OperateException(String message) {
        super(message);
    }
    public OperateException(String message, HTTPCode httpCode) {
      super(message);
      this.httpCode=httpCode;
    }
}
