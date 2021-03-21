package site.lanmushan.auth.api.service;

import site.lanmushan.auth.api.bo.BoAuthFkDeptPost;
import site.lanmushan.auth.api.entity.AuthFkDeptPost;
import site.lanmushan.framework.query.service.BaseService;


import java.util.List;

/**
 * 角色和部门关联表(AuthFkDeptPost)表服务接口
 *
 * @author dy
 * @since 2020-06-15 22:13:47
 */
public interface AuthFkDeptPostService extends BaseService<AuthFkDeptPost> {

    /**
     * 新增数据
     *
     * @param authFkDeptPost 实例对象
     * @return 实例对象
     */
    void insertService(BoAuthFkDeptPost authFkDeptPost);

    /**
     * 修改数据
     *
     * @param authFkDeptPost 实例对象
     * @return 实例对象
     */
    void updateService(BoAuthFkDeptPost authFkDeptPost);

    /**
     * 批量新增
     *
     * @param boAuthFkDeptPostList 实例对象
     * @return
     */
    void insertServiceList(List<BoAuthFkDeptPost> boAuthFkDeptPostList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}