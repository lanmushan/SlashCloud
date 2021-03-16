package site.lanmushan.cms.api.service;

import site.lanmushan.cms.api.bo.BoCmsTbRequestMapping;
import site.lanmushan.cms.api.entity.CmsTbRequestMapping;
import site.lanmushan.framework.query.service.BaseService;

import java.util.List;

/**
 * (CmsTbRequestMapping)表服务接口
 *
 * @author dy
 * @since 2021-02-07 20:45:36
 */
public interface CmsTbRequestMappingService extends BaseService<CmsTbRequestMapping> {


    /**
     * 新增数据
     *
     * @param cmsTbRequestMapping 实例对象
     * @return 实例对象
     */
    void insertService(BoCmsTbRequestMapping cmsTbRequestMapping);

    /**
     * 修改数据
     *
     * @param cmsTbRequestMapping 实例对象
     * @return 实例对象
     */
    void updateService(BoCmsTbRequestMapping cmsTbRequestMapping);

    /**
     * 批量新增
     *
     * @param boCmsTbRequestMappingList 实例对象
     * @return
     */
    void insertServiceList(List<BoCmsTbRequestMapping> boCmsTbRequestMappingList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}