package com.lanmushan.authservice.service;

import com.lanmushan.authservice.bo.BoAuthTbPost;

import java.util.List;

import com.lanmushan.framework.service.BaseService;
import com.lanmushan.authservice.entity.AuthTbPost;

/**
 * 岗位信息表(AuthTbPost)表服务接口
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
public interface AuthTbPostService extends BaseService<AuthTbPost> {


    /**
     * 新增数据
     *
     * @param authTbPost 实例对象
     * @return 实例对象
     */
    void insertService(BoAuthTbPost authTbPost);

    /**
     * 修改数据
     *
     * @param authTbPost 实例对象
     * @return 实例对象
     */
    void updateService(BoAuthTbPost authTbPost);

    /**
     * 批量新增
     *
     * @param boAuthTbPostList 实例对象
     * @return
     */
    void insertServiceList(List<BoAuthTbPost> boAuthTbPostList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}