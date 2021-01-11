package site.lanmushan.framework.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.entity.ControllerApiInfo;
import site.lanmushan.framework.util.api.ApiScannerUtils;

import java.util.List;

/**
 * @author cayden
 * @date 2020/7/5 23:25
 */
@RestController
@Api(tags = "API服务接口")
public class ApiScannerController {
    @Value("${spring.application.name}")
    private String appName;
    @ApiOperation("查询该服务所有接口")
    @GetMapping(value = "/selectApiList")
    public Message getAllApiInfo(){
        Message message = Message.getInstance("查询成功");
        List<ControllerApiInfo> apiInfos = ApiScannerUtils.initApiList();
        apiInfos.forEach(it->{
            it.setAppName(appName);
        });
        message.setRows(apiInfos);
        return message;
    }
}
