package com.lanmushan.authservice.service;

import com.lanmushan.authservice.bo.BoAuthTbDept;

import java.util.List;

import com.lanmushan.framework.service.BaseService;
import com.lanmushan.authservice.entity.AuthTbDept;

/**
 * 部门表(AuthTbDept)表服务接口
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
public interface AuthTbDeptService extends BaseService<AuthTbDept> {


    /**
     * 新增数据
     *
     * @param authTbDept 实例对象
     * @return 实例对象
     */
    void insertService(BoAuthTbDept authTbDept);

    /**
     * 修改数据
     *
     * @param authTbDept 实例对象
     * @return 实例对象
     */
    void updateService(BoAuthTbDept authTbDept);

    /**
     * 批量新增
     *
     * @param boAuthTbDeptList 实例对象
     * @return
     */
    void insertServiceList(List<BoAuthTbDept> boAuthTbDeptList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}