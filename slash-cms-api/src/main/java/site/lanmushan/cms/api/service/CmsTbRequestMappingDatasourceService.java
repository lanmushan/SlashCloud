package site.lanmushan.cms.api.service;

import site.lanmushan.cms.api.bo.BoCmsTbRequestMappingDatasource;
import site.lanmushan.cms.api.entity.CmsTbRequestMappingDatasource;
import site.lanmushan.framework.query.service.BaseService;

import java.util.List;

/**
 * (CmsTbRequestMappingDatasource)表服务接口
 *
 * @author dy
 * @since 2021-02-08 20:35:00
 */
public interface CmsTbRequestMappingDatasourceService extends BaseService<CmsTbRequestMappingDatasource> {


    /**
     * 新增数据
     *
     * @param cmsTbRequestMappingDatasource 实例对象
     * @return 实例对象
     */
    void insertService(BoCmsTbRequestMappingDatasource cmsTbRequestMappingDatasource);

    /**
     * 修改数据
     *
     * @param cmsTbRequestMappingDatasource 实例对象
     * @return 实例对象
     */
    void updateService(BoCmsTbRequestMappingDatasource cmsTbRequestMappingDatasource);

    /**
     * 批量新增
     *
     * @param boCmsTbRequestMappingDatasourceList 实例对象
     * @return
     */
    void insertServiceList(List<BoCmsTbRequestMappingDatasource> boCmsTbRequestMappingDatasourceList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}