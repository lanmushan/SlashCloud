package com.lanmushan.dyauthservice.service.impl;


import com.lanmushan.dyauthservice.mapper.AuthFkRoleResourceMapper;
import com.lanmushan.dyauthservice.service.AuthFkRoleResourceService;
import com.lanmushan.framework.constant.HTTPCode;
import com.lanmushan.framework.exception.OperateException;
import com.lanmushan.dyauthservice.bo.BoAuthFkRoleResource;
import com.lanmushan.dyauthservice.entity.AuthFkRoleResource;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.util.date.DateUtil;
import com.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


/**
 * (AuthFkRoleResource)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 22:13:47
 */
@Service("authFkRoleResourceService")
public class AuthFkRoleResourceServiceImpl implements AuthFkRoleResourceService {
    @Autowired
    private AuthFkRoleResourceMapper authFkRoleResourceMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return authFkRoleResourceMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoAuthFkRoleResource boAuthFkRoleResource) {
        Date now = DateUtil.now();
        boAuthFkRoleResource.setCreateTime(now);
        boAuthFkRoleResource.setUpdateTime(now);
        authFkRoleResourceMapper.insertSelective(boAuthFkRoleResource);
    }

    @Override
    public void insertServiceList(List<BoAuthFkRoleResource> boAuthFkRoleResourceList) {
        Date now = DateUtil.now();
        boAuthFkRoleResourceList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        authFkRoleResourceMapper.insertList(boAuthFkRoleResourceList);
    }

    @Override
    public void updateService(BoAuthFkRoleResource boAuthFkRoleResource) {
        boAuthFkRoleResource.setUpdateTime(DateUtil.now());
        int reuslt = authFkRoleResourceMapper.updateByPrimaryKeySelective(boAuthFkRoleResource);
        if (reuslt != 1) {
            throw new OperateException("新增失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        authFkRoleResourceMapper.deleteByIdList(ids);
    }
}