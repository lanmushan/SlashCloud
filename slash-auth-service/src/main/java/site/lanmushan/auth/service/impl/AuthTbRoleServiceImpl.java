package site.lanmushan.auth.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lanmushan.auth.api.bo.BoAuthTbRole;
import site.lanmushan.auth.api.entity.AuthTbRole;
import site.lanmushan.auth.api.service.AuthTbRoleService;
import site.lanmushan.auth.mapper.AuthTbRoleMapper;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.util.utils.DateUtil;
import site.lanmushan.framework.uuid.MyUUID;

import java.util.Date;
import java.util.List;


/**
 * 角色表(AuthTbRole)表服务实现类
 *
 * @author dy
 * @since 2020-07-13 21:28:55
 */
@Service("authTbRoleService")
public class AuthTbRoleServiceImpl implements AuthTbRoleService {
    @Autowired
    private AuthTbRoleMapper authTbRoleMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return authTbRoleMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoAuthTbRole boAuthTbRole) {
        Date now = DateUtil.now();
        boAuthTbRole.setCreateTime(now);
        boAuthTbRole.setUpdateTime(now);
        authTbRoleMapper.insertSelective(boAuthTbRole);
    }

    @Override
    public void insertServiceList(List<BoAuthTbRole> boAuthTbRoleList) {
        Date now = DateUtil.now();
        boAuthTbRoleList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        authTbRoleMapper.insertList(boAuthTbRoleList);
    }

    @Override
    public void updateService(BoAuthTbRole boAuthTbRole) {
        boAuthTbRole.setUpdateTime(DateUtil.now());
        int reuslt = authTbRoleMapper.updateByPrimaryKeySelective(boAuthTbRole);
        if (reuslt != 1) {
            throw new OperateException("更新失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        if (ids != null) {
            ids.forEach(it -> {
                AuthTbRole authTbRole = authTbRoleMapper.selectByPrimaryKey(it);
                if (CurrentUserUtil.ADMIN_CODE.equals(authTbRole.getRoleCode())) {
                    throw new OperateException("不能删除管理员角色");
                }
            });
            authTbRoleMapper.deleteByIdList(ids);
        }

    }
}