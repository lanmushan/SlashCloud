package site.lanmushan.auth.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lanmushan.auth.api.bo.BoAuthTbUser;
import site.lanmushan.auth.api.entity.AuthTbUser;
import site.lanmushan.auth.api.vo.VoAuthTbUser;
import site.lanmushan.auth.mapper.AuthTbRoleMapper;
import site.lanmushan.auth.mapper.AuthTbUserMapper;
import site.lanmushan.auth.service.AuthTbUserService;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.cypher.md5.MD5Util;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.util.utils.DateUtil;
import site.lanmushan.framework.util.utils.StringCommonUtil;
import site.lanmushan.framework.uuid.MyUUID;

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

    public static final String DEFAULT_PASSWORD = MD5Util.createMD532("123456");

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

    @Override
    public void resetLoginPassword(Long userId, String password) {
        String salt = StringCommonUtil.getRandomString(6);
        if (password == null) {
            password = DEFAULT_PASSWORD;
        }
        String newPassword = CurrentUserUtil.createPassword(password, salt);
        AuthTbUser authTbUser = new AuthTbUser();
        authTbUser.setId(userId);
        authTbUser.setLoginPassword(newPassword);
        authTbUser.setSalt(salt);
        authTbUserMapper.updateByPrimaryKeySelective(authTbUser);
    }
}