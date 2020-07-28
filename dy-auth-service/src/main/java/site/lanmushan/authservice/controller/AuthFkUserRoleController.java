package site.lanmushan.authservice.controller;

import site.lanmushan.authservice.entity.AuthFkUserRole;
import site.lanmushan.authservice.service.AuthFkUserRoleService;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.authservice.bo.BoAuthFkUserRole;
import site.lanmushan.authservice.mapper.AuthFkUserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;
/**
 * 用户角色关系表(AuthFkUserRole)表控制层
 *
 * @author dy
 * @since 2020-07-13 21:33:05
 */
@RestController
@RequestMapping("/authFkUserRole")
@Slf4j
public class AuthFkUserRoleController {
      @Autowired
    private AuthFkUserRoleMapper authFkUserRoleMapper;
    @Autowired
    private AuthFkUserRoleService authFkUserRoleService;
 
    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthFkUserRole authFkUserRole= authFkUserRoleMapper.selectByPrimaryKey(id);
        msg.setRow(authFkUserRole);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoAuthFkUserRole obj) {
        Message msg = new Message();
        authFkUserRoleService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }
    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoAuthFkUserRole obj) {
        Message msg = new Message();
        authFkUserRoleService.updateService(obj);
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
        authFkUserRoleService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

}