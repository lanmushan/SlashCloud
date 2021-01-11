package site.lanmushan.auth.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import site.lanmushan.auth.api.bo.BoAuthTbDept;
import site.lanmushan.auth.api.entity.AuthTbDept;

import site.lanmushan.auth.mapper.AuthTbDeptMapper;
import site.lanmushan.auth.service.AuthTbDeptService;
import site.lanmushan.framework.annotations.RequestQueryInfo;
import site.lanmushan.framework.util.TreeUtil;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.dto.QueryInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;

/**
 * 部门表(AuthTbDept)表控制层
 *
 * @author dy
 * @since 2020-07-02 22:14:54
 */
@RestController
@RequestMapping("/authTbDept")
@Slf4j
@Api(tags = "部门服务接口")
public class AuthTbDeptController {
    @Autowired
    private AuthTbDeptMapper authTbDeptMapper;
    @Autowired
    private AuthTbDeptService authTbDeptService;

    @ApiOperation("根据ID查询部门")
    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthTbDept authTbDept = authTbDeptMapper.selectByPrimaryKey(id);
        msg.setRow(authTbDept);
        return msg;
    }

    @ApiOperation("查询部门树形列表")
    @GetMapping("/selectTreeList")
    public Message selectTreeList(@RequestQueryInfo QueryInfo queryInfo) {
        List deptList = authTbDeptMapper.selectList(queryInfo);
        return Message.getInstance().setRows(TreeUtil.listToTree(deptList));
    }

    @ApiOperation("新增")
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
    @ApiOperation("修改")
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
    @ApiOperation("删除")
    @PostMapping("/delete")
    public Message delete(@RequestParam List<Long> ids) {
        Message msg = new Message();
        authTbDeptService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

}