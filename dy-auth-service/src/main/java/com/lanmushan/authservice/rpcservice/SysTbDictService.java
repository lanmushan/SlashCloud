package com.lanmushan.authservice.rpcservice;

import com.lanmushan.authservice.rpcservice.hystric.SchedualServiceHiHystric;
import com.lanmushan.framework.dto.QueryInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (SysTbDict)表服务接口
 *
 * @author daiyu
 * @since 2020-06-21 16:23:40
 */
@FeignClient(value = "sys-service", fallback = SchedualServiceHiHystric.class)
public interface SysTbDictService {


    /**
     * 新增数据
     *
     * @param queryInfo 实例对象
     * @return 实例对象
     */
    @GetMapping(value = "/SysTbDictGroup/selectList")
    List<SysTbDictGroup> selectList(QueryInfo queryInfo);


    @GetMapping(value = "/SysTbDictGroup/selectById")
    SysTbDictGroup selectById(@RequestParam("id") Long id);

}