package site.lanmushan.authservice.controller;

import io.swagger.annotations.Api;
import site.lanmushan.authservice.entity.AuthTbRole;
import site.lanmushan.authservice.service.AuthTbRoleService;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.authservice.bo.BoAuthTbRole;
import site.lanmushan.authservice.mapper.AuthTbRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

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
@Api(tags = "角色服务接口")
public class AuthTbRoleController {
      @Autowired
    private AuthTbRoleMapper authTbRoleMapper;
    @Autowired
    private AuthTbRoleService authTbRoleService;
 
    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthTbRole authTbRole= authTbRoleMapper.selectByPrimaryKey(id);
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