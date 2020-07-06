package com.lanmushan.authservice.controller;

import com.lanmushan.authservice.entity.AuthTbUserLoginLog;
import com.lanmushan.authservice.service.AuthTbUserLoginLogService;
import org.springframework.web.bind.annotation.*;
import com.lanmushan.framework.dto.Message;
import com.lanmushan.authservice.bo.BoAuthTbUserLoginLog;
import com.lanmushan.authservice.mapper.AuthTbUserLoginLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户登录记录(AuthTbUserLoginLog)表控制层
 *
 * @author makejava
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
    @DeleteMapping("/delete")
    public Message delete(@RequestParam List<Long> ids) {
        Message msg = new Message();
        authTbUserLoginLogService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

}