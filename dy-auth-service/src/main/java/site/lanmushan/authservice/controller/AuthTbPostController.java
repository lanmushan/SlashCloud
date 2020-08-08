package site.lanmushan.authservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import site.lanmushan.authservice.entity.AuthTbPost;
import site.lanmushan.authservice.service.AuthTbPostService;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.authservice.bo.BoAuthTbPost;
import site.lanmushan.authservice.mapper.AuthTbPostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

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
@Api(tags = "部门服务接口")
public class AuthTbPostController {
    @Autowired
    private AuthTbPostMapper authTbPostMapper;
    @Autowired
    private AuthTbPostService authTbPostService;

    @ApiOperation("根据ID查询")
    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthTbPost authTbPost = authTbPostMapper.selectByPrimaryKey(id);
        msg.setRow(authTbPost);
        return msg;
    }

    @ApiOperation("新增")
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
    @ApiOperation("修改")
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
    @ApiOperation("删除")
    public Message delete(@RequestParam List<Long> ids) {
        Message msg = new Message();
        authTbPostService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

}