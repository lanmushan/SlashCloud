package site.lanmushan.auth.api.service;

import site.lanmushan.auth.api.bo.BoAuthFkRoleResource;
import site.lanmushan.auth.api.entity.AuthFkRoleResource;
import site.lanmushan.auth.api.req.RoleRelationResource;
import site.lanmushan.framework.query.service.BaseService;


import java.util.List;

/**
 * (AuthFkRoleResource)表服务接口
 *
 * @author dy
 * @since 2020-06-15 22:13:47
 */
public interface AuthFkRoleResourceService extends BaseService<AuthFkRoleResource> {


    /**
     * 新增数据
     *
     * @param authFkRoleResource 实例对象
     * @return 实例对象
     */
    void insertService(BoAuthFkRoleResource authFkRoleResource);

    /**
     * 修改数据
     *
     * @param authFkRoleResource 实例对象
     * @return 实例对象
     */
    void updateService(BoAuthFkRoleResource authFkRoleResource);

    /**
     * 批量新增
     *
     * @param boAuthFkRoleResourceList 实例对象
     * @return
     */
    void insertServiceList(List<BoAuthFkRoleResource> boAuthFkRoleResourceList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);

    /**
     * 角色关联多个资源，以前的直接删掉
     * @param roleRelationResource
     */
    void updateRoleRelationResource(RoleRelationResource roleRelationResource);
}