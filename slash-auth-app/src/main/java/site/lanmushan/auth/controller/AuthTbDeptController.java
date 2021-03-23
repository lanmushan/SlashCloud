package site.lanmushan.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.auth.api.bo.BoAuthTbDept;
import site.lanmushan.auth.api.entity.AuthTbDept;
import site.lanmushan.auth.mapper.AuthTbDeptMapper;
import site.lanmushan.auth.api.service.AuthTbDeptService;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.query.annotations.RequestQueryInfo;
import site.lanmushan.framework.query.util.TreeUtil;

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
    @PostMapping("/delete")
    public Message delete(@RequestBody @Valid BoAuthTbDept obj) {
        Message msg = new Message();
        authTbDeptService.deleteServiceByIds(obj.getIds());
        msg.success("删除成功");
        return msg;
    }

}