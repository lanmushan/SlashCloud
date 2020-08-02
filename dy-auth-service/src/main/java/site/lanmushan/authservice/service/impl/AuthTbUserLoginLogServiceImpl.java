package site.lanmushan.authservice.service.impl;


import site.lanmushan.authservice.mapper.AuthTbUserLoginLogMapper;
import site.lanmushan.authservice.service.AuthTbUserLoginLogService;
import site.lanmushan.authservice.bo.BoAuthTbUserLoginLog;

import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.util.date.DateUtil;
import site.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 用户登录记录(AuthTbUserLoginLog)表服务实现类
 *
 * @author dy
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