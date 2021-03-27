package site.lanmushan.auth.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lanmushan.auth.api.bo.BoAuthTbResource;
import site.lanmushan.auth.api.entity.AuthFkRoleResource;
import site.lanmushan.auth.api.service.AuthTbResourceService;
import site.lanmushan.auth.api.vo.VoAuthTbResource;
import site.lanmushan.auth.mapper.AuthFkRoleResourceMapper;
import site.lanmushan.auth.mapper.AuthTbResourceMapper;
import site.lanmushan.auth.mapper.AuthTbRoleMapper;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.util.utils.DateUtil;
import site.lanmushan.framework.uuid.MyUUID;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 菜单表(AuthTbResource)表服务实现类
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
@Service("authTbResourceService")
public class AuthTbResourceServiceImpl implements AuthTbResourceService {
    @Autowired
    private AuthTbResourceMapper authTbResourceMapper;
    @Autowired
    private AuthTbRoleMapper authTbRoleMapper;
    @Autowired
    private AuthFkRoleResourceMapper authFkRoleResourceMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        List<VoAuthTbResource> resourcesList = authTbResourceMapper.selectList(queryInfo);
        resourcesList.forEach(it -> {
            it.setAuthTbRoleList(authTbRoleMapper.selectRolesByResourceId(it.getId()));
        });
        return resourcesList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertService(BoAuthTbResource boAuthTbResource) {
        Date now = DateUtil.now();
        boAuthTbResource.setCreateTime(now);
        boAuthTbResource.setUpdateTime(now);
        authTbResourceMapper.insertSelective(boAuthTbResource);
        List<AuthFkRoleResource> authFkRoleResources = createAuthFkRoleResourceList(boAuthTbResource.getId(), boAuthTbResource.getRoleCodeList());
        if (authFkRoleResources != null) {
            authFkRoleResourceMapper.insertList(authFkRoleResources);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertServiceList(List<BoAuthTbResource> boAuthTbResourceList) {
        Date now = DateUtil.now();
        boAuthTbResourceList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
            List<AuthFkRoleResource> authFkRoleResources = createAuthFkRoleResourceList(it.getId(), it.getRoleCodeList());
            if (authFkRoleResources != null&&authFkRoleResources.size()>0) {
                authFkRoleResourceMapper.insertList(authFkRoleResources);
            }
        });
        authTbResourceMapper.insertList(boAuthTbResourceList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateService(BoAuthTbResource boAuthTbResource) {
        Date now = DateUtil.now();
        boAuthTbResource.setUpdateTime(now);
        AuthFkRoleResource deleteQueryAuthFkRoleResource = new AuthFkRoleResource();
        deleteQueryAuthFkRoleResource.setFkResourceId(boAuthTbResource.getId());
        authFkRoleResourceMapper.delete(deleteQueryAuthFkRoleResource);
        List<AuthFkRoleResource> authFkRoleResources = createAuthFkRoleResourceList(boAuthTbResource.getId(), boAuthTbResource.getRoleCodeList());
        if (authFkRoleResources != null&&authFkRoleResources.size()>0) {
            authFkRoleResourceMapper.insertList(authFkRoleResources);
        }
        authTbResourceMapper.updateByPrimaryKeySelective(boAuthTbResource);

    }

    List<AuthFkRoleResource> createAuthFkRoleResourceList(Long resourceId, List<String> roleCodes) {
        Date now = DateUtil.now();
        if (roleCodes != null) {
            List<AuthFkRoleResource> authFkRoleResourceList = new ArrayList<>(roleCodes.size());
            roleCodes.forEach(it -> {
                AuthFkRoleResource authFkRoleResource = new AuthFkRoleResource();
                authFkRoleResource.setCreateTime(now);
                authFkRoleResource.setUpdateTime(now);
                authFkRoleResource.setId(MyUUID.getInstance().nextId());
                authFkRoleResource.setFkResourceId(resourceId);
                authFkRoleResource.setFkRoleCode(it);
                authFkRoleResourceList.add(authFkRoleResource);
            });
            return authFkRoleResourceList;
        }
        return null;
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        authTbResourceMapper.deleteByIdList(ids);
    }
}