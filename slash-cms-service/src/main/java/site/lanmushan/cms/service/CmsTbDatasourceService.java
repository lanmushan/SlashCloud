package site.lanmushan.cms.service;

import site.lanmushan.cms.api.bo.BoCmsTbDatasource;
import site.lanmushan.cms.api.entity.CmsTbDatasource;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.query.annotations.RequestQueryInfo;
import site.lanmushan.framework.query.service.BaseService;
import site.lanmushan.framework.query.util.TreeUtil;

import java.util.List;

/**
 * (CmsTbDatasource)表服务接口
 *
 * @author dy
 * @since 2021-01-30 14:59:53
 */
public interface CmsTbDatasourceService extends BaseService<CmsTbDatasource> {


    /**
     * 新增数据
     *
     * @param cmsTbDatasource 实例对象
     * @return 实例对象
     */
    void insertService(BoCmsTbDatasource cmsTbDatasource);

    /**
     * 修改数据
     *
     * @param cmsTbDatasource 实例对象
     * @return 实例对象
     */
    void updateService(BoCmsTbDatasource cmsTbDatasource);

    /**
     * 批量新增
     *
     * @param boCmsTbDatasourceList 实例对象
     * @return
     */
    void insertServiceList(List<BoCmsTbDatasource> boCmsTbDatasourceList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}