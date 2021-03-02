package site.lanmushan.cms.service;

import site.lanmushan.cms.api.bo.BoCmsTbDatasourceType;
import site.lanmushan.cms.api.entity.CmsTbDatasourceType;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.query.service.BaseService;

import java.util.List;

/**
 * (CmsTbDatasourceType)表服务接口
 *
 * @author dy
 * @since 2021-01-30 14:59:53
 */
public interface CmsTbDatasourceTypeService extends BaseService<CmsTbDatasourceType> {


    /**
     * 新增数据
     *
     * @param cmsTbDatasourceType 实例对象
     * @return 实例对象
     */
    void insertService(BoCmsTbDatasourceType cmsTbDatasourceType);

    /**
     * 修改数据
     *
     * @param cmsTbDatasourceType 实例对象
     * @return 实例对象
     */
    void updateService(BoCmsTbDatasourceType cmsTbDatasourceType);

    /**
     * 批量新增
     *
     * @param boCmsTbDatasourceTypeList 实例对象
     * @return
     */
    void insertServiceList(List<BoCmsTbDatasourceType> boCmsTbDatasourceTypeList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);

    /**
     * 查询TreeList
     *
     * @param queryInfo
     * @return
     */
    List selectTreeList(QueryInfo queryInfo);
}