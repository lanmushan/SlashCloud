package com.lanmushan.authservice.controller;

import com.lanmushan.authservice.entity.AuthTbRole;
import com.lanmushan.authservice.service.AuthTbRoleService;
import org.springframework.web.bind.annotation.*;
import com.lanmushan.framework.dto.Message;
import com.lanmushan.authservice.bo.BoAuthTbRole;
import com.lanmushan.authservice.mapper.AuthTbRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色表(AuthTbRole)表控制层
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@RestController
@RequestMapping("/authTbRole")
@Slf4j
public class AuthTbRoleController {
    @Autowired
    private AuthTbRoleMapper authTbRoleMapper;
    @Autowired
    private AuthTbRoleService authTbRoleService;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthTbRole authTbRole = authTbRoleMapper.selectByPrimaryKey(id);
        msg.setRow(authTbRole);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoAuthTbRole obj) {
        Message msg = new Message();
        authTbRoleService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoAuthTbRole obj) {
        Message msg = new Message();
        authTbRoleService.updateService(obj);
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
        authTbRoleService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

}