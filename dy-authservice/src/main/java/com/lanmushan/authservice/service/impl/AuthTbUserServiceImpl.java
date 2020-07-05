package com.lanmushan.authservice.service.impl;


import com.lanmushan.authservice.mapper.AuthTbRoleMapper;
import com.lanmushan.authservice.mapper.AuthTbUserMapper;
import com.lanmushan.authservice.service.AuthTbUserService;
import com.lanmushan.authservice.vo.VoAuthTbUser;
import com.lanmushan.framework.constant.HTTPCode;
import com.lanmushan.framework.exception.OperateException;
import com.lanmushan.authservice.bo.BoAuthTbUser;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.util.date.DateUtil;
import com.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


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
    @Autowired
    private AuthTbRoleMapper authTbRoleMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {

        List<VoAuthTbUser> list = authTbUserMapper.selectList(queryInfo);
        list.forEach(it -> {
            it.setAuthTbRoleList(authTbRoleMapper.selectRolesByUserId(it.getId()));
        });
        return list;
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