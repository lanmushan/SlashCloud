package site.lanmushan.framework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.entity.DaiYuApiInfo;
import site.lanmushan.framework.util.api.ApiScannerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cayden
 * @date 2020/7/5 23:25
 */
@RestController
@Api(tags = "API服务接口")

@RequestMapping("/authService")
public class ApiScannerController {
    @ApiOperation("查询该服务所有接口")
    @GetMapping(value = "/getAllApiInfo")
    public Message getAllApiInfo(){
        Message message = Message.getInstance("查询成功");
        List<DaiYuApiInfo> daiYuApiInfos = ApiScannerUtils.initApiList();
        message.setRows(daiYuApiInfos);
        return message;
    }
}
