package com.lanmushan.framework.service;

import com.lanmushan.framework.dto.QueryInfo;
import springfox.documentation.annotations.Cacheable;


import java.util.List;
/**
 * @author Administrator
 */
public interface BaseService<T> {
    /**
     * 列表查询
     * @param queryInfo
     * @return
     */
    List<T> selectList(QueryInfo queryInfo);
}
