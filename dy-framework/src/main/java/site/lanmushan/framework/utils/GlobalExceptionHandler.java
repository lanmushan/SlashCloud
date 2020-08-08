package site.lanmushan.framework.utils;

import com.alibaba.fastjson.JSONObject;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.Message;

import lombok.extern.slf4j.Slf4j;
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
 *
 * @author dy
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理其他异常
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Message allExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        log.error(e.getMessage(), e);
        Message msg = new Message();
        msg.setRow(e.getMessage());
        msg.setHttpCode(HTTPCode.S500);
        return msg;
    }

    /**
     * 操作异常处理
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = OperateException.class)
    @ResponseBody
    public Message CustomExceptionHandler(HttpServletRequest request, OperateException e) throws Exception {
        Message msg = new Message();
        log.error(e.getMessage(), e);
        if (null != e.getInput()) {
            String input = "";
            if (e.getInput() instanceof String) {
                input = e.getInput().toString();
            } else {
                input = JSONObject.toJSONString(e.getInput());
            }
            log.error("输入报文{}", input);
        }
        if (e.getHttpCode() != null) {
            msg.setHttpCode(e.getHttpCode());
            msg.error(e.getHttpCode(), e.getMessage());
        } else {
            msg.error(HTTPCode.E205, e.getMessage());

        }
        return msg;
    }

    /**
     * 数据校验
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Message BindExceptionHandler(HttpServletRequest request, BindException e) throws Exception {
        log.error(e.getMessage(), e);
        return handerBindingResult(e.getBindingResult(), e.getMessage());
    }

    /**
     * json数据校验
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Message BindMethodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) throws Exception {
        log.error(e.getMessage(), e);
        return handerBindingResult(e.getBindingResult(), e.getMessage());
    }

    private Message handerBindingResult(BindingResult result, String errorMsg) {
        Message msg = new Message();
        if (result.hasErrors()) {
            List<FieldError> err = result.getFieldErrors();
            int i = 0;
            for (FieldError fe : err) {
                msg.addError(fe.getField(), fe.getDefaultMessage());
                if (i == 0) {
                    msg.error(fe.getDefaultMessage());
                    i++;
                }
            }
        }
        msg.setCode(HTTPCode.C400.code);
        return msg;
    }

}
