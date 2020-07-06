package com.lanmushan.authservice.controller;

import com.lanmushan.authservice.entity.AuthTbDept;
import com.lanmushan.authservice.service.AuthTbDeptService;
import com.lanmushan.authservice.vo.VoAuthTbDept;
import com.lanmushan.framework.annotations.RequestQueryInfo;
import com.lanmushan.framework.dto.DHashMap;
import com.lanmushan.framework.util.TreeUtil;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;
import com.lanmushan.framework.dto.Message;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.authservice.bo.BoAuthTbDept;
import com.lanmushan.authservice.mapper.AuthTbDeptMapper;
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
 * @since 2020-07-02 22:14:54
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

    @GetMapping("/selectTreeList")
    public Message selectTreeList(@RequestQueryInfo QueryInfo queryInfo) {
        List deptList = authTbDeptMapper.selectList(queryInfo);
        return Message.getInstance().setRows(TreeUtil.listToTree(deptList));
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