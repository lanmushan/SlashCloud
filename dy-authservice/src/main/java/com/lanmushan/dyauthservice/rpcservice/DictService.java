package com.lanmushan.dyauthservice.rpcservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 */
@FeignClient(value = "DICT-SERVICE", fallback = SchedualServiceHiHystric.class)
public interface DictService {
    @RequestMapping(value = "/Admin/dict/test2")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
