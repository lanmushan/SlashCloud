package site.lanmushan.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.auth.api.bo.BoAuthTbRole;
import site.lanmushan.auth.api.entity.AuthTbRole;
import site.lanmushan.auth.mapper.AuthTbRoleMapper;
import site.lanmushan.auth.service.AuthTbRoleService;
import site.lanmushan.framework.dto.Message;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色表(AuthTbRole)表控制层
 *
 * @author dy
 * @since 2020-07-13 21:28:55
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
    @PostMapping("/delete")
    public Message delete(@RequestParam List<Long> ids) {
        Message msg = new Message();
        authTbRoleService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

}