package site.lanmushan.authservice.service.impl;


import site.lanmushan.authservice.mapper.AuthTbRoleMapper;
import site.lanmushan.authservice.service.AuthTbRoleService;
import site.lanmushan.authservice.bo.BoAuthTbRole;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.utils.OperateException;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.util.date.DateUtil;
import site.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
      return  authTbRoleMapper.selectList(queryInfo);
    }
    @Override
    public void insertService(BoAuthTbRole boAuthTbRole) {
         Date now= DateUtil.now();
         boAuthTbRole.setCreateTime(now);
         boAuthTbRole.setUpdateTime(now);
         authTbRoleMapper.insertSelective(boAuthTbRole);
    }
      @Override
    public  void insertServiceList(List<BoAuthTbRole> boAuthTbRoleList) {
      Date now= DateUtil.now();
      boAuthTbRoleList.forEach(it->{
          it.setCreateTime(now);
          it.setUpdateTime(now);
          it.setId(MyUUID.getInstance().nextId());
      });
        authTbRoleMapper.insertList(boAuthTbRoleList);
    }
        @Override
    public void updateService(BoAuthTbRole boAuthTbRole) {
        boAuthTbRole.setUpdateTime(DateUtil.now());
        int reuslt= authTbRoleMapper.updateByPrimaryKeySelective(boAuthTbRole);
        if(reuslt!=1)
        {
            throw new OperateException("新增失败", HTTPCode.Fail);
        }
    }
        @Override
    public void deleteServiceByIds(List<Long> ids) {
        authTbRoleMapper.deleteByIdList(ids);
    }
}