package site.lanmushan.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.auth.api.bo.BoAuthTbDatascope;
import site.lanmushan.auth.api.entity.AuthTbDatascope;
import site.lanmushan.auth.mapper.AuthTbDatascopeMapper;
import site.lanmushan.auth.api.service.AuthTbDatascopeService;
import site.lanmushan.framework.dto.Message;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户表(AuthTbDatascope)表控制层
 *
 * @author dy
 * @since 2021-03-07 16:26:50
 */
@RestController
@RequestMapping("/authTbDatascope")
@Slf4j
public class AuthTbDatascopeController {
    @Autowired
    private AuthTbDatascopeMapper authTbDatascopeMapper;
    @Autowired
    private AuthTbDatascopeService authTbDatascopeService;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthTbDatascope authTbDatascope = authTbDatascopeMapper.selectByPrimaryKey(id);
        msg.setRow(authTbDatascope);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody List<BoAuthTbDatascope> list) {
        Message msg = new Message();
        authTbDatascopeService.insertServiceList(list);
        msg.setRow(list).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoAuthTbDatascope obj) {
        Message msg = new Message();
        authTbDatascopeService.updateService(obj);
        msg.setRow(obj).success("更新成功");
        return msg;
    }

    /**
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    public Message delete(@RequestBody BoAuthTbDatascope obj) {
        Message msg = new Message();
        authTbDatascopeService.deleteServiceByIds(obj.getIds());
        msg.success("删除成功");
        return msg;
    }

}