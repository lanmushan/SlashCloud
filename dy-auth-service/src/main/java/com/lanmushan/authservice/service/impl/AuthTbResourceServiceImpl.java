package com.lanmushan.authservice.service.impl;


import com.lanmushan.authservice.mapper.AuthTbResourceMapper;
import com.lanmushan.authservice.service.AuthTbResourceService;
import com.lanmushan.framework.constant.HTTPCode;
import com.lanmushan.framework.exception.OperateException;
import com.lanmushan.authservice.bo.BoAuthTbResource;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.util.date.DateUtil;
import com.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 菜单表(AuthTbResource)表服务实现类
 *
 * @author daiyu
 * @since 2020-06-15 22:13:48
 */
@Service("authTbResourceService")
public class AuthTbResourceServiceImpl implements AuthTbResourceService {
    @Autowired
    private AuthTbResourceMapper authTbResourceMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return authTbResourceMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoAuthTbResource boAuthTbResource) {
        Date now = DateUtil.now();
        boAuthTbResource.setCreateTime(now);
        boAuthTbResource.setUpdateTime(now);
        authTbResourceMapper.insertSelective(boAuthTbResource);
    }

    @Override
    public void insertServiceList(List<BoAuthTbResource> boAuthTbResourceList) {
        Date now = DateUtil.now();
        boAuthTbResourceList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        authTbResourceMapper.insertList(boAuthTbResourceList);
    }

    @Override
    public void updateService(BoAuthTbResource boAuthTbResource) {
        boAuthTbResource.setUpdateTime(DateUtil.now());
        int reuslt = authTbResourceMapper.updateByPrimaryKeySelective(boAuthTbResource);
        if (reuslt != 1) {
            throw new OperateException("新增失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        authTbResourceMapper.deleteByIdList(ids);
    }
}