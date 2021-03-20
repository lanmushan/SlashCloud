package site.lanmushan.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.auth.api.bo.BoAuthTbUser;
import site.lanmushan.auth.api.entity.AuthTbUser;
import site.lanmushan.auth.api.service.AuthTbUserService;
import site.lanmushan.auth.mapper.AuthTbUserMapper;
import site.lanmushan.auth.req.RejectUser;
import site.lanmushan.framework.authorization.CurrentUser;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.query.controller.BaseController;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户表(AuthTbUser)表控制层
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
@RestController
@RequestMapping("/authTbUser")
@Slf4j

public class AuthTbUserController extends BaseController {
    @Autowired
    private AuthTbUserMapper authTbUserMapper;
    @Autowired
    private AuthTbUserService authTbUserService;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 查询单个用户信息
     *
     * @param id 用户ID
     * @return
     */
    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthTbUser authTbUser = authTbUserMapper.selectByPrimaryKey(id);
        msg.setRow(authTbUser);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody BoAuthTbUser obj) {
        Message msg = new Message();
        authTbUserService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoAuthTbUser obj) {
        Message msg = new Message();
        authTbUserService.updateService(obj);
        msg.setRow(obj).success("更新成功");
        return msg;
    }

    /**
     * 删除用户信息
     *
     * @param ids 用户ID列表
     * @return code=200 操作成功
     */
    @PostMapping("/delete")
    public Message delete(@RequestParam List<Long> ids) {
        Message msg = new Message();
        authTbUserService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

    /**
     * 删除用户信息
     *
     * @param rejectUser 剔除账号
     * @return code=200 操作成功
     */
    @PostMapping("/rejectUser")
    public Message rejectUser(@RequestBody RejectUser rejectUser) {
        Message msg = new Message();
        if (rejectUser == null || rejectUser.getAccount()==null) {
            msg.error("剔除账号错误");
            return msg;
        }
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser();
        if (!currentUser.isAdmin()) {
            msg.error("只有管理员可以剔除在线用户");
            return msg;
        }
        if (currentUser.getAccount().equals(rejectUser.getAccount())) {
            msg.error("不能剔除自己");
            return msg;
        }
        redisTemplate.delete(CurrentUserUtil.REDIS_ONLINE_USER_KEY_PREFIX+rejectUser.getAccount());
        msg.success("剔除成功");
        return msg;
    }

}