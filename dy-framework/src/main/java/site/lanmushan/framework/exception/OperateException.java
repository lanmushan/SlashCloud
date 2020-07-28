package site.lanmushan.framework.exception;

import site.lanmushan.framework.constant.HTTPCode;

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
    public OperateException( HTTPCode httpCode) {
        super(httpCode.msg);
        this.httpCode=httpCode;

    }
}
