package com.lanmushan.authservice.service;

import com.lanmushan.authservice.bo.BoAuthTbUser;

import java.util.List;

import com.lanmushan.framework.service.BaseService;
import com.lanmushan.authservice.entity.AuthTbUser;

/**
 * 用户表(AuthTbUser)表服务接口
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
public interface AuthTbUserService extends BaseService<AuthTbUser> {


    /**
     * 新增数据
     *
     * @param authTbUser 实例对象
     * @return 实例对象
     */
    void insertService(BoAuthTbUser authTbUser);

    /**
     * 修改数据
     *
     * @param authTbUser 实例对象
     * @return 实例对象
     */
    void updateService(BoAuthTbUser authTbUser);

    /**
     * 批量新增
     *
     * @param boAuthTbUserList 实例对象
     * @return
     */
    void insertServiceList(List<BoAuthTbUser> boAuthTbUserList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}