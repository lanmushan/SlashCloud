package site.lanmushan.cms.service;

import site.lanmushan.cms.api.bo.BoCmsTbCategory;
import site.lanmushan.cms.api.entity.CmsTbCategory;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.query.annotations.RequestQueryInfo;
import site.lanmushan.framework.query.service.BaseService;
import site.lanmushan.framework.query.util.TreeUtil;

import java.util.List;

/**
 * (CmsTbCategory)表服务接口
 *
 * @author dy
 * @since 2021-01-30 14:59:51
 */
public interface CmsTbCategoryService extends BaseService<CmsTbCategory> {


    /**
     * 新增数据
     *
     * @param cmsTbCategory 实例对象
     * @return 实例对象
     */
    void insertService(BoCmsTbCategory cmsTbCategory);

    /**
     * 修改数据
     *
     * @param cmsTbCategory 实例对象
     * @return 实例对象
     */
    void updateService(BoCmsTbCategory cmsTbCategory);

    /**
     * 批量新增
     *
     * @param boCmsTbCategoryList 实例对象
     * @return
     */
    void insertServiceList(List<BoCmsTbCategory> boCmsTbCategoryList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);

    /**
     * 树形结构查询
     *
     * @param queryInfo
     * @return
     */
    List selectTreeList(QueryInfo queryInfo);
}