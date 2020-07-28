package site.lanmushan.authservice.service;

import site.lanmushan.authservice.bo.BoAuthFkUserRole;
import java.util.List;

import site.lanmushan.authservice.entity.AuthFkUserRole;
import site.lanmushan.framework.service.BaseService;

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
     * @param  boAuthFkUserRoleList 实例对象
     * @return
     */
      void insertServiceList(List<BoAuthFkUserRole> boAuthFkUserRoleList);

      /**
     * 批量删除
     * @param ids
     * @return
     */
      void deleteServiceByIds(List<Long> ids);


}