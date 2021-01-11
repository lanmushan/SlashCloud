package site.lanmushan.framework.mapper;

import site.lanmushan.framework.dto.QueryInfo;


import java.util.List;

public interface SelectQueryMapper<T>{
    /**
     * 列表查询
     * @param queryInfo
     * @return
     */
     List selectList(QueryInfo queryInfo);

    /**
     * 根据id查询详情
     * @param queryInfo
     * @return
     */
     T selectQueryById(QueryInfo queryInfo);
}
