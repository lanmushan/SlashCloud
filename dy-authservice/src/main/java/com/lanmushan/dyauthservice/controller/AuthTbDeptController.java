package com.lanmushan.dyauthservice.controller;

import com.lanmushan.dyauthservice.entity.AuthTbDept;
import com.lanmushan.dyauthservice.service.AuthTbDeptService;
import org.springframework.web.bind.annotation.*;
import com.lanmushan.framework.dto.Message;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.dyauthservice.bo.BoAuthTbDept;
import com.lanmushan.dyauthservice.mapper.AuthTbDeptMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 部门表(AuthTbDept)表控制层
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@RestController
@RequestMapping("/authTbDept")
@Slf4j
public class AuthTbDeptController {
    @Autowired
    private AuthTbDeptMapper authTbDeptMapper;
    @Autowired
    private AuthTbDeptService authTbDeptService;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthTbDept authTbDept = authTbDeptMapper.selectByPrimaryKey(id);
        msg.setRow(authTbDept);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoAuthTbDept obj) {
        Message msg = new Message();
        authTbDeptService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoAuthTbDept obj) {
        Message msg = new Message();
        authTbDeptService.updateService(obj);
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
        authTbDeptService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

}