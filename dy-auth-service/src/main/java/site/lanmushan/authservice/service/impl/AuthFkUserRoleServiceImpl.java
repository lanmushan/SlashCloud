package site.lanmushan.authservice.service.impl;


import site.lanmushan.authservice.mapper.AuthFkUserRoleMapper;
import site.lanmushan.authservice.service.AuthFkUserRoleService;
import site.lanmushan.authservice.bo.BoAuthFkUserRole;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.annotations.EnabledQuickSelect;

import site.lanmushan.framework.util.date.DateUtil;
import site.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 用户角色关系表(AuthFkUserRole)表服务实现类
 *
 * @author dy
 * @since 2020-07-13 21:33:05
 */
@Service("authFkUserRoleService")
public class AuthFkUserRoleServiceImpl implements AuthFkUserRoleService {
     @Autowired
    private AuthFkUserRoleMapper authFkUserRoleMapper;
      @Override
      @EnabledQuickSelect
      public List selectList(QueryInfo queryInfo) {
      return  authFkUserRoleMapper.selectList(queryInfo);
    }
    @Override
    public void insertService(BoAuthFkUserRole boAuthFkUserRole) {
         Date now= DateUtil.now();
         boAuthFkUserRole.setCreateTime(now);
         boAuthFkUserRole.setUpdateTime(now);
         authFkUserRoleMapper.insertSelective(boAuthFkUserRole);
    }
      @Override
    public  void insertServiceList(List<BoAuthFkUserRole> boAuthFkUserRoleList) {
      Date now= DateUtil.now();
      boAuthFkUserRoleList.forEach(it->{
          it.setCreateTime(now);
          it.setUpdateTime(now);
          it.setId(MyUUID.getInstance().nextId());
      });
        authFkUserRoleMapper.insertList(boAuthFkUserRoleList);
    }
        @Override
    public void updateService(BoAuthFkUserRole boAuthFkUserRole) {
        boAuthFkUserRole.setUpdateTime(DateUtil.now());
        int reuslt= authFkUserRoleMapper.updateByPrimaryKeySelective(boAuthFkUserRole);
        if(reuslt!=1)
        {
            throw new OperateException("新增失败", HTTPCode.Fail);
        }
    }
        @Override
    public void deleteServiceByIds(List<Long> ids) {
        authFkUserRoleMapper.deleteByIdList(ids);
    }
}