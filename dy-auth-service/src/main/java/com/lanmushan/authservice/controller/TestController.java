package com.lanmushan.authservice.controller;

import com.lanmushan.authservice.rpcservice.SysTbDictGroup;
import com.lanmushan.authservice.rpcservice.SysTbDictService;
import com.lanmushan.framework.annotations.RequestQueryInfo;
import com.lanmushan.framework.dto.Message;
import com.lanmushan.framework.dto.QueryInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author dy
 * @Date 2020/6/21 16:31
 * @Version 1.0
 */
@RestController
@Slf4j
public class TestController {
    @Autowired
    SysTbDictService sysTbDictService;

    @GetMapping("/")
    public Message index(@RequestQueryInfo QueryInfo queryInfo) {
        log.info(queryInfo.toString());
        List list = sysTbDictService.selectList(queryInfo);
        Message msg = new Message();
        msg.setRows(list);
        return msg;

    }

    @GetMapping("test")
    public Message test(@RequestQueryInfo QueryInfo queryInfo) {
        log.info(queryInfo.toString());
        List list = sysTbDictService.selectList(queryInfo);
        Message msg = new Message();
        msg.setRows(list);
        return msg;

    }

    @GetMapping("test2")
    public Message test(@RequestParam Long id) {

        SysTbDictGroup sysTbDictGroup = sysTbDictService.selectById(id);
        Message msg = new Message();
        msg.setRow(sysTbDictGroup);
        return msg;

    }
}
