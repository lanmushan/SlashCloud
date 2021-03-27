package site.lanmushan.framework.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.swagger.util.ApiScannerUtils;


/**
 * @author cayden
 * @date 2020/7/5 23:25
 */
@RestController
@Api(tags = "API服务接口")
public class ApiScannerController {
    @Autowired
    ApplicationContext applicationContext;

    @ApiOperation("查询该服务所有接口")
    @GetMapping(value = "/getAllApiInfo")
    public Message getAllApiInfo() {
        Message message = Message.getInstance();
        message.setRows(ApiScannerUtils.getAllURL(applicationContext));
        return message;
    }
}
