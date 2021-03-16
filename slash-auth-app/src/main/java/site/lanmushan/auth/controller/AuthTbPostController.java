package site.lanmushan.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.auth.api.bo.BoAuthTbPost;
import site.lanmushan.auth.api.entity.AuthTbPost;
import site.lanmushan.auth.mapper.AuthTbPostMapper;
import site.lanmushan.auth.api.service.AuthTbPostService;
import site.lanmushan.framework.dto.Message;

import javax.validation.Valid;
import java.util.List;

/**
 * 岗位信息表(AuthTbPost)表控制层
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
@RestController
@RequestMapping("/authTbPost")
@Slf4j
public class AuthTbPostController {
    @Autowired
    private AuthTbPostMapper authTbPostMapper;
    @Autowired
    private AuthTbPostService authTbPostService;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthTbPost authTbPost = authTbPostMapper.selectByPrimaryKey(id);
        msg.setRow(authTbPost);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoAuthTbPost obj) {
        Message msg = new Message();
        authTbPostService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoAuthTbPost obj) {
        Message msg = new Message();
        authTbPostService.updateService(obj);
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
        authTbPostService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

}