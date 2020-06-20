package com.lanmushan.dyauthservice.service.impl;


import com.lanmushan.dyauthservice.mapper.AuthTbUserLoginLogMapper;
import com.lanmushan.dyauthservice.service.AuthTbUserLoginLogService;
import com.lanmushan.framework.constant.HTTPCode;
import com.lanmushan.framework.exception.OperateException;
import com.lanmushan.dyauthservice.bo.BoAuthTbUserLoginLog;
import com.lanmushan.dyauthservice.entity.AuthTbUserLoginLog;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.util.date.DateUtil;
import com.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


/**
 * 用户登录记录(AuthTbUserLoginLog)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@Service("authTbUserLoginLogService")
public class AuthTbUserLoginLogServiceImpl implements AuthTbUserLoginLogService {
    @Autowired
    private AuthTbUserLoginLogMapper authTbUserLoginLogMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return authTbUserLoginLogMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoAuthTbUserLoginLog boAuthTbUserLoginLog) {
        Date now = DateUtil.now();
        boAuthTbUserLoginLog.setCreateTime(now);
        boAuthTbUserLoginLog.setUpdateTime(now);
        authTbUserLoginLogMapper.insertSelective(boAuthTbUserLoginLog);
    }

    @Override
    public void insertServiceList(List<BoAuthTbUserLoginLog> boAuthTbUserLoginLogList) {
        Date now = DateUtil.now();
        boAuthTbUserLoginLogList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        authTbUserLoginLogMapper.insertList(boAuthTbUserLoginLogList);
    }

    @Override
    public void updateService(BoAuthTbUserLoginLog boAuthTbUserLoginLog) {
        boAuthTbUserLoginLog.setUpdateTime(DateUtil.now());
        int reuslt = authTbUserLoginLogMapper.updateByPrimaryKeySelective(boAuthTbUserLoginLog);
        if (reuslt != 1) {
            throw new OperateException("新增失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        authTbUserLoginLogMapper.deleteByIdList(ids);
    }
}