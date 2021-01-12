package site.lanmushan.framework.exception;


import site.lanmushan.framework.constant.HTTPCode;

/**
 * 自定义异常
 *
 * @author Administrator
 */
public class OperateException extends BaseException {
    public HTTPCode httpCode;
    private Object input;

    public OperateException(String message) {
        super(message);
    }

    public OperateException(String message, HTTPCode httpCode) {
        super(message);
        this.httpCode = httpCode;
    }

    public OperateException(String message, HTTPCode httpCode, Object input) {
        super(message);
        this.httpCode = httpCode;
        this.input = input;
    }

    public OperateException(HTTPCode httpCode) {
        super(httpCode.msg);
        this.httpCode = httpCode;
    }

    public Object getInput() {
        return input;
    }

    public void setInput(Object input) {
        this.input = input;
    }

    public HTTPCode getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HTTPCode httpCode) {
        this.httpCode = httpCode;
    }
}
