package site.lanmushan.framework.query.mapper;

import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.query.annotations.ConditionDisable;

import java.util.List;

/**
 * @author Administrator
 */
@ConditionDisable
public interface SelectQueryMapper<T> {
    /**
     * 列表查询
     *
     * @param queryInfo
     * @return
     */
    List selectList(QueryInfo queryInfo);

    /**
     * 根据id查询详情
     *
     * @param queryInfo
     * @return
     */
    T selectQueryById(QueryInfo queryInfo);
}
