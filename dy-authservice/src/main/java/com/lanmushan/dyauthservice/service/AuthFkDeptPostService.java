package com.lanmushan.dyauthservice.service;

import com.lanmushan.dyauthservice.bo.BoAuthFkDeptPost;

import java.util.List;

import com.lanmushan.framework.service.BaseService;
import com.lanmushan.dyauthservice.entity.AuthFkDeptPost;

/**
 * 角色和部门关联表(AuthFkDeptPost)表服务接口
 *
 * @author makejava
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