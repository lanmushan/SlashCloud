package site.lanmushan.auth.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lanmushan.auth.api.bo.BoAuthFkRoleResource;

import site.lanmushan.auth.mapper.AuthFkRoleResourceMapper;
import site.lanmushan.auth.service.AuthFkRoleResourceService;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.util.utils.DateUtil;
import site.lanmushan.framework.uuid.MyUUID;

import java.util.Date;
import java.util.List;


/**
 * (AuthFkRoleResource)表服务实现类
 *
 * @author dy
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