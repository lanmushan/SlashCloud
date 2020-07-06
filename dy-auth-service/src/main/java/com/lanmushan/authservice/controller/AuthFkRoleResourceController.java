package com.lanmushan.authservice.controller;

import com.lanmushan.authservice.entity.AuthFkRoleResource;
import com.lanmushan.authservice.service.AuthFkRoleResourceService;
import org.springframework.web.bind.annotation.*;
import com.lanmushan.framework.dto.Message;
import com.lanmushan.authservice.bo.BoAuthFkRoleResource;
import com.lanmushan.authservice.mapper.AuthFkRoleResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;

/**
 * (AuthFkRoleResource)表控制层
 *
 * @author makejava
 * @since 2020-06-15 22:13:47
 */
@RestController
@RequestMapping("/authFkRoleResource")
@Slf4j
public class AuthFkRoleResourceController {
    @Autowired
    private AuthFkRoleResourceMapper authFkRoleResourceMapper;
    @Autowired
    private AuthFkRoleResourceService authFkRoleResourceService;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthFkRoleResource authFkRoleResource = authFkRoleResourceMapper.selectByPrimaryKey(id);
        msg.setRow(authFkRoleResource);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoAuthFkRoleResource obj) {
        Message msg = new Message();
        authFkRoleResourceService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoAuthFkRoleResource obj) {
        Message msg = new Message();
        authFkRoleResourceService.updateService(obj);
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
        authFkRoleResourceService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

}