package com.lanmushan.framework.exception;

import com.lanmushan.framework.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
     private Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 处理其他异常
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public Message allExceptionHandler(HttpServletRequest request, Exception e) throws Exception
    {
        log.error(e.getMessage(),e);
        Message msg=new Message();
        msg.setCode(ErrorCode.UNKNOWN_ERROR);
        msg.error("服务器内部错误");
        msg.setRow(e.getMessage());
        return msg;
}

    /**
     * 操作异常处理
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value=OperateException.class)
    @ResponseBody
    public Message CustomExceptionHandler(HttpServletRequest request, OperateException e) throws Exception
    {
        Message msg=new Message();
        log.error(e.getMessage(),e);
        msg.error(e.getMessage());
        if(null!=e.getCode())
             msg.setCode(e.getCode());
        return msg;
    }

    /**
     * 数据校验
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value=BindException.class)
    @ResponseBody
    public Message BindExceptionHandler(HttpServletRequest request, BindException e) throws Exception
    {
        log.error(e.getMessage(),e);
        Message msg=new Message();
        BindingResult result=e.getBindingResult();
        if (result.hasErrors()) {
            List<FieldError> err = result.getFieldErrors();
            int i=0;
            for (FieldError fe : err) {
                msg.addError(fe.getField().toString(), fe.getDefaultMessage());
                if(i==0)
                {
                    msg.error(fe.getDefaultMessage());
                    i++;
                }
            }
            msg.setCode(101);
            return msg;
        }
        msg.error(e.getMessage());
        return msg;
    }
    /**
     * json数据校验
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    @ResponseBody
    public Message BindMethodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) throws Exception
    {
        log.error(e.getMessage(),e);
        Message msg=new Message();
        BindingResult result=e.getBindingResult();
        if (result.hasErrors()) {
            List<FieldError> err = result.getFieldErrors();
            int i=0;
            for (FieldError fe : err) {
                msg.addError(fe.getField().toString(), fe.getDefaultMessage());
                if(i==0)
                {
                    msg.error(fe.getDefaultMessage());
                    i++;
                }
            }
            msg.setCode(101);
            return msg;

        }
        msg.error(e.getMessage());
        return msg;
    }


}
