package site.lanmushan.cms.service;

import site.lanmushan.cms.api.bo.BoCmsTbContent;
import site.lanmushan.cms.api.entity.CmsTbContent;
import site.lanmushan.framework.query.service.BaseService;

import java.util.List;

/**
 * 文章(CmsTbContent)表服务接口
 *
 * @author dy
 * @since 2021-01-30 14:59:52
 */
public interface CmsTbContentService extends BaseService<CmsTbContent> {


    /**
     * 新增数据
     *
     * @param cmsTbContent 实例对象
     * @return 实例对象
     */
    void insertService(BoCmsTbContent cmsTbContent);

    /**
     * 修改数据
     *
     * @param cmsTbContent 实例对象
     * @return 实例对象
     */
    void updateService(BoCmsTbContent cmsTbContent);

    /**
     * 批量新增
     *
     * @param boCmsTbContentList 实例对象
     * @return
     */
    void insertServiceList(List<BoCmsTbContent> boCmsTbContentList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}