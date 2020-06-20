package com.lanmushan.dyauthservice.service.impl;


import com.lanmushan.dyauthservice.mapper.AuthTbUserMapper;
import com.lanmushan.dyauthservice.service.AuthTbUserService;
import com.lanmushan.framework.constant.HTTPCode;
import com.lanmushan.framework.exception.OperateException;
import com.lanmushan.dyauthservice.bo.BoAuthTbUser;
import com.lanmushan.dyauthservice.entity.AuthTbUser;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.util.date.DateUtil;
import com.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


/**
 * 用户表(AuthTbUser)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@Service("authTbUserService")
public class AuthTbUserServiceImpl implements AuthTbUserService {
    @Autowired
    private AuthTbUserMapper authTbUserMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return authTbUserMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoAuthTbUser boAuthTbUser) {
        Date now = DateUtil.now();
        boAuthTbUser.setCreateTime(now);
        boAuthTbUser.setUpdateTime(now);
        authTbUserMapper.insertSelective(boAuthTbUser);
    }

    @Override
    public void insertServiceList(List<BoAuthTbUser> boAuthTbUserList) {
        Date now = DateUtil.now();
        boAuthTbUserList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        authTbUserMapper.insertList(boAuthTbUserList);
    }

    @Override
    public void updateService(BoAuthTbUser boAuthTbUser) {
        boAuthTbUser.setUpdateTime(DateUtil.now());
        int reuslt = authTbUserMapper.updateByPrimaryKeySelective(boAuthTbUser);
        if (reuslt != 1) {
            throw new OperateException("新增失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        authTbUserMapper.deleteByIdList(ids);
    }
}