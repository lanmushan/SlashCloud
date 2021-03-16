package site.lanmushan.sys.api.service;

import site.lanmushan.framework.query.service.BaseService;
import site.lanmushan.sys.api.bo.BoSysTbDict;
import site.lanmushan.sys.api.entity.SysTbDict;

import java.util.List;

/**
 * (SysTbDict)表服务接口
 *
 * @author dy
 * @since 2020-06-14 21:15:03
 */
public interface SysTbDictService extends BaseService<SysTbDict> {


    /**
     * 新增数据
     *
     * @param sysTbDict 实例对象
     * @return 实例对象
     */
    void insertService(BoSysTbDict sysTbDict);

    /**
     * 修改数据
     *
     * @param sysTbDict 实例对象
     * @return 实例对象
     */
    void updateService(BoSysTbDict sysTbDict);

    /**
     * 批量新增
     *
     * @param boSysTbDictList 实例对象
     * @return
     */
    void insertServiceList(List<BoSysTbDict> boSysTbDictList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}