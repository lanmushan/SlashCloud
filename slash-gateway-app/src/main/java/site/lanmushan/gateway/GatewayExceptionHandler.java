package site.lanmushan.gateway;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.exception.OperateException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@Slf4j
@ControllerAdvice
public class GatewayExceptionHandler {
    /**
     * 处理其他异常
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public Message allExceptionHandler(HttpServletRequest request, Throwable e) throws Exception {
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

}
