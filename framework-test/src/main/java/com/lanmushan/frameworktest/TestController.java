package com.lanmushan.frameworktest;

import com.lanmushan.framework.controller.BaseController;
import com.lanmushan.framework.dto.QueryInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends BaseController {
    @RequestMapping("/test1")
    public String test(QueryInfo queryInfo){
        System.out.println(queryInfo);
        startPage();
        return "11";
    }
}
