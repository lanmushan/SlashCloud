package site.lanmushan.auth.service;

import site.lanmushan.auth.api.bo.BoAuthFkUserRole;
import site.lanmushan.auth.api.entity.AuthFkUserRole;
import site.lanmushan.framework.query.service.BaseService;


import java.util.List;

/**
 * 用户角色关系表(AuthFkUserRole)表服务接口
 *
 * @author dy
 * @since 2020-07-13 21:33:05
 */
public interface AuthFkUserRoleService extends BaseService<AuthFkUserRole> {


    /**
     * 新增数据
     *
     * @param authFkUserRole 实例对象
     * @return 实例对象
     */
    void insertService(BoAuthFkUserRole authFkUserRole);

    /**
     * 修改数据
     *
     * @param authFkUserRole 实例对象
     * @return 实例对象
     */
    void updateService(BoAuthFkUserRole authFkUserRole);

    /**
     * 批量新增
     *
     * @param boAuthFkUserRoleList 实例对象
     * @return
     */
    void insertServiceList(List<BoAuthFkUserRole> boAuthFkUserRoleList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}