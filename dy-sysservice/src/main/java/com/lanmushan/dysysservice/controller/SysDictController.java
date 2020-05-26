package com.lanmushan.dysysservice.controller;

import com.lanmushan.dysysservice.entity.SysDict;
import com.lanmushan.dysysservice.mapper.SysDictMapper;
import com.lanmushan.framework.controller.BaseController;
import com.lanmushan.framework.dto.QueryInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author dy
 * @Date 2020/5/26 21:30
 * @Version 1.0
 */
@RequestMapping("/Admin/SysDict")
@RestController
public class SysDictController extends BaseController {
    @Autowired
    SysDictMapper sysDictMapper;
    @GetMapping("/{aaa}/test")
    public String test(@PathVariable("aaa") String aa, QueryInfo queryInfo) {
        System.out.println(aa);
        List list=sysDictMapper.selectAll();
        return Integer.toString(list.size());
    }
}
