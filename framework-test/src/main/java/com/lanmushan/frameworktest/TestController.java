package com.lanmushan.frameworktest;

import com.alibaba.fastjson.JSON;
import com.lanmushan.framework.annotations.RequestQueryInfo;
import com.lanmushan.framework.controller.BaseController;
import com.lanmushan.framework.dto.QueryInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController extends BaseController {
    @RequestMapping("/test1")
    public String test(QueryInfo queryInfo){
       System.out.println(111);
        startPage();
        return "11";
    }
    @RequestMapping( value = "/test2")
    public String test2(@RequestQueryInfo QueryInfo queryInfo){
        log.info(JSON.toJSONString(queryInfo));
        startPage();
        return "11";
    }
}
