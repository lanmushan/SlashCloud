package site.lanmushan.authservice.service.impl;


import site.lanmushan.authservice.mapper.AuthTbRoleMapper;
import site.lanmushan.authservice.mapper.AuthTbUserMapper;
import site.lanmushan.authservice.service.AuthTbUserService;
import site.lanmushan.authservice.vo.VoAuthTbUser;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.authservice.bo.BoAuthTbUser;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.util.date.DateUtil;
import site.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 用户表(AuthTbUser)表服务实现类
 *
 * @author dy
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