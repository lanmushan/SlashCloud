package site.lanmushan.auth.api.service;

import site.lanmushan.auth.api.bo.BoAuthTbDatascope;
import site.lanmushan.auth.api.entity.AuthTbDatascope;
import site.lanmushan.framework.query.service.BaseService;

import java.util.List;

/**
 * 用户表(AuthTbDatascope)表服务接口
 *
 * @author dy
 * @since 2021-03-07 16:25:51
 */
public interface AuthTbDatascopeService extends BaseService<AuthTbDatascope> {


    /**
     * 新增数据
     *
     * @param authTbDatascope 实例对象
     * @return 实例对象
     */
    void insertService(BoAuthTbDatascope authTbDatascope);

    /**
     * 修改数据
     *
     * @param authTbDatascope 实例对象
     * @return 实例对象
     */
    void updateService(BoAuthTbDatascope authTbDatascope);

    /**
     * 批量新增
     *
     * @param boAuthTbDatascopeList 实例对象
     * @return
     */
    void insertServiceList(List<BoAuthTbDatascope> boAuthTbDatascopeList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}