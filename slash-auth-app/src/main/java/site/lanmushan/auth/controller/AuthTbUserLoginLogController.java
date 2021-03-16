package site.lanmushan.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.auth.api.bo.BoAuthTbUserLoginLog;
import site.lanmushan.auth.api.entity.AuthTbUserLoginLog;
import site.lanmushan.auth.mapper.AuthTbUserLoginLogMapper;
import site.lanmushan.auth.api.service.AuthTbUserLoginLogService;
import site.lanmushan.framework.dto.Message;

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