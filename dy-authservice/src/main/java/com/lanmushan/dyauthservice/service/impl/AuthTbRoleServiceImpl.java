package com.lanmushan.dyauthservice.service.impl;


import com.lanmushan.dyauthservice.mapper.AuthTbRoleMapper;
import com.lanmushan.dyauthservice.service.AuthTbRoleService;
import com.lanmushan.framework.constant.HTTPCode;
import com.lanmushan.framework.exception.OperateException;
import com.lanmushan.dyauthservice.bo.BoAuthTbRole;
import com.lanmushan.dyauthservice.entity.AuthTbRole;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.util.date.DateUtil;
import com.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


/**
 * 角色表(AuthTbRole)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@Service("authTbRoleService")
public class AuthTbRoleServiceImpl implements AuthTbRoleService {
    @Autowired
    private AuthTbRoleMapper authTbRoleMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return authTbRoleMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoAuthTbRole boAuthTbRole) {
        Date now = DateUtil.now();
        boAuthTbRole.setCreateTime(now);
        boAuthTbRole.setUpdateTime(now);
        authTbRoleMapper.insertSelective(boAuthTbRole);
    }

    @Override
    public void insertServiceList(List<BoAuthTbRole> boAuthTbRoleList) {
        Date now = DateUtil.now();
        boAuthTbRoleList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        authTbRoleMapper.insertList(boAuthTbRoleList);
    }

    @Override
    public void updateService(BoAuthTbRole boAuthTbRole) {
        boAuthTbRole.setUpdateTime(DateUtil.now());
        int reuslt = authTbRoleMapper.updateByPrimaryKeySelective(boAuthTbRole);
        if (reuslt != 1) {
            throw new OperateException("新增失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        authTbRoleMapper.deleteByIdList(ids);
    }
}