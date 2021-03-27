package site.lanmushan.auth.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lanmushan.auth.api.bo.BoAuthTbUser;
import site.lanmushan.auth.api.entity.AuthFkUserRole;
import site.lanmushan.auth.api.entity.AuthTbUser;
import site.lanmushan.auth.api.service.AuthTbUserService;
import site.lanmushan.auth.api.vo.VoAuthTbUser;
import site.lanmushan.auth.mapper.AuthFkUserRoleMapper;
import site.lanmushan.auth.mapper.AuthTbRoleMapper;
import site.lanmushan.auth.mapper.AuthTbUserMapper;
import site.lanmushan.framework.authorization.CurrentUser;
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
    @Autowired
    private AuthFkUserRoleMapper authFkUserRoleMapper;
    @Value("${slash.default.password:123456}")
    private String default_password;

    @Override
    public List selectList(QueryInfo queryInfo) {

        List<VoAuthTbUser> list = authTbUserMapper.selectList(queryInfo);
        list.forEach(it -> {
            it.setAuthTbRoleList(authTbRoleMapper.selectRolesByUserId(it.getId()));
        });
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertService(BoAuthTbUser boAuthTbUser) {
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser();
        Date now = DateUtil.now();
        boAuthTbUser.setCreateTime(now);
        boAuthTbUser.setUpdateTime(now);
        String salt = StringCommonUtil.getRandomString(6);

        String newPassword = CurrentUserUtil.createPassword(boAuthTbUser.getLoginPassword(), salt);
        boAuthTbUser.setLoginPassword(newPassword);
        boAuthTbUser.setSalt(salt);
        authTbUserMapper.insertSelective(boAuthTbUser);
        if (boAuthTbUser.getRoleCodeList() != null && boAuthTbUser.getRoleCodeList().size() > 0) {
            boAuthTbUser.getRoleCodeList().forEach(it -> {
                AuthFkUserRole authFkUserRole = new AuthFkUserRole();
                authFkUserRole.setCreateTime(now);
                authFkUserRole.setUpdateTime(now);
                authFkUserRole.setFkRoleCode(it);
                authFkUserRole.setCreateUserAccount(currentUser.getAccount());
                authFkUserRole.setUpdateUserAccount(currentUser.getAccount());
                authFkUserRole.setFkUserId(boAuthTbUser.getId());
                authFkUserRoleMapper.insertSelective(authFkUserRole);
            });
        }
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
        Date now=DateUtil.now();
        boAuthTbUser.setUpdateTime(DateUtil.now());
        authTbUserMapper.updateByPrimaryKeySelective(boAuthTbUser);
        AuthFkUserRole delQuery=new AuthFkUserRole();
        delQuery.setFkUserId(boAuthTbUser.getId());
        authFkUserRoleMapper.delete(delQuery);
        if (boAuthTbUser.getRoleCodeList() != null && boAuthTbUser.getRoleCodeList().size() > 0) {
            boAuthTbUser.getRoleCodeList().forEach(it -> {
                AuthFkUserRole authFkUserRole = new AuthFkUserRole();
                authFkUserRole.setCreateTime(now);
                authFkUserRole.setUpdateTime(now);
                authFkUserRole.setFkRoleCode(it);
                authFkUserRole.setUpdateUserAccount(CurrentUserUtil.getCurrentUser().getAccount());
                authFkUserRole.setFkUserId(boAuthTbUser.getId());
                authFkUserRoleMapper.insertSelective(authFkUserRole);
            });
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
            password = MD5Util.createMD532(this.default_password);
        }
        String newPassword = CurrentUserUtil.createPassword(password, salt);
        AuthTbUser authTbUser = new AuthTbUser();
        authTbUser.setId(userId);
        authTbUser.setLoginPassword(newPassword);
        authTbUser.setSalt(salt);
        authTbUserMapper.updateByPrimaryKeySelective(authTbUser);
    }
}