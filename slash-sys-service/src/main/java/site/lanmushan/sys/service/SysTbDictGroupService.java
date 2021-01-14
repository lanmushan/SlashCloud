package site.lanmushan.sys.service;

import site.lanmushan.framework.query.service.BaseService;
import site.lanmushan.sys.api.bo.BoSysTbDictGroup;
import site.lanmushan.sys.api.entity.SysTbDictGroup;

import java.util.List;

/**
 * (SysTbDictGroup)表服务接口
 *
 * @author dy
 * @since 2020-06-14 21:15:05
 */
public interface SysTbDictGroupService extends BaseService<SysTbDictGroup> {


    /**
     * 新增数据
     *
     * @param sysTbDictGroup 实例对象
     * @return 实例对象
     */
    void insertService(BoSysTbDictGroup sysTbDictGroup);

    /**
     * 修改数据
     *
     * @param sysTbDictGroup 实例对象
     * @return 实例对象
     */
    void updateService(BoSysTbDictGroup sysTbDictGroup);

    /**
     * 批量新增
     *
     * @param boSysTbDictGroupList 实例对象
     * @return
     */
    void insertServiceList(List<BoSysTbDictGroup> boSysTbDictGroupList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}