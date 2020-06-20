package com.lanmushan.dyauthservice.controller;

import com.lanmushan.dyauthservice.entity.AuthFkDeptPost;
import com.lanmushan.dyauthservice.service.AuthFkDeptPostService;
import org.springframework.web.bind.annotation.*;
import com.lanmushan.framework.dto.Message;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.dyauthservice.bo.BoAuthFkDeptPost;
import com.lanmushan.dyauthservice.mapper.AuthFkDeptPostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 角色和部门关联表(AuthFkDeptPost)表控制层
 *
 * @author makejava
 * @since 2020-06-15 21:54:32
 */
@RestController
@RequestMapping("/authFkDeptPost")
@Slf4j
public class AuthFkDeptPostController {
    @Autowired
    private AuthFkDeptPostMapper authFkDeptPostMapper;
    @Autowired
    private AuthFkDeptPostService authFkDeptPostService;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthFkDeptPost authFkDeptPost = authFkDeptPostMapper.selectByPrimaryKey(id);
        msg.setRow(authFkDeptPost);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoAuthFkDeptPost obj) {
        Message msg = new Message();
        authFkDeptPostService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoAuthFkDeptPost obj) {
        Message msg = new Message();
        authFkDeptPostService.updateService(obj);
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
        authFkDeptPostService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

}