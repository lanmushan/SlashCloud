package site.lanmushan.authservice.controller;

import io.swagger.annotations.Api;
import site.lanmushan.authservice.entity.AuthTbUserLoginLog;
import site.lanmushan.authservice.service.AuthTbUserLoginLogService;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.authservice.bo.BoAuthTbUserLoginLog;
import site.lanmushan.authservice.mapper.AuthTbUserLoginLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户登录记录(AuthTbUserLoginLog)表控制层
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
@RestController
@RequestMapping("/authTbUserLoginLog")
@Slf4j
@Api(tags = "登录记录服务接口")
public class AuthTbUserLoginLogController {
    @Autowired
    private AuthTbUserLoginLogMapper authTbUserLoginLogMapper;
    @Autowired
    private AuthTbUserLoginLogService authTbUserLoginLogService;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthTbUserLoginLog authTbUserLoginLog = authTbUserLoginLogMapper.selectByPrimaryKey(id);
        msg.setRow(authTbUserLoginLog);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoAuthTbUserLoginLog obj) {
        Message msg = new Message();
        authTbUserLoginLogService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoAuthTbUserLoginLog obj) {
        Message msg = new Message();
        authTbUserLoginLogService.updateService(obj);
        msg.setRow(obj).success("更新成功");
        return msg;
    }

    /**
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public Message delete(@RequestParam List<Long> ids) {
        Message msg = new Message();
        authTbUserLoginLogService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

}