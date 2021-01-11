package site.lanmushan.query.service;

import site.lanmushan.framework.dto.QueryInfo;

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
