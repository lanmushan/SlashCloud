package site.lanmushan.framework.query.service;

import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.query.annotations.EnabledQuickSelect;

import java.util.List;

/**
 * @author Administrator
 */
public interface BaseService<T> {
    /**
     * 列表查询
     *
     * @param queryInfo
     * @return
     */
    List selectList(QueryInfo queryInfo);
}
