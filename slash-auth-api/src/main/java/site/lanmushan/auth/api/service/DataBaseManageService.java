package site.lanmushan.auth.api.service;

import site.lanmushan.framework.dto.DHashMap;

import java.util.List;

/**
 * @author Administrator
 */
public interface DataBaseManageService {
    /**
     * 查询所有数据表
     *
     * @return
     */
    List<DHashMap> selectAllTableList(String appName);

    /**
     * 查询所有表字段
     *
     * @return
     */
    List<DHashMap> selectTableAllTableFieldList(String appName,String tableName);

}
