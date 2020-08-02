package site.lanmushan.authservice.service.impl;


import site.lanmushan.authservice.mapper.AuthTbDeptMapper;
import site.lanmushan.authservice.service.AuthTbDeptService;
import site.lanmushan.authservice.bo.BoAuthTbDept;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.util.date.DateUtil;
import site.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 部门表(AuthTbDept)表服务实现类
 *
 * @author dy
 * @since 2020-07-02 22:14:54
 */
@Service("authTbDeptService")
public class AuthTbDeptServiceImpl implements AuthTbDeptService {
    @Autowired
    private AuthTbDeptMapper authTbDeptMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return authTbDeptMapper.selectList(queryInfo);
    }
    @Override
    public void insertService(BoAuthTbDept boAuthTbDept) {
        Date now = DateUtil.now();
        boAuthTbDept.setCreateTime(now);
        boAuthTbDept.setUpdateTime(now);
        authTbDeptMapper.insertSelective(boAuthTbDept);
    }

    @Override
    public void insertServiceList(List<BoAuthTbDept> boAuthTbDeptList) {
        Date now = DateUtil.now();
        boAuthTbDeptList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        authTbDeptMapper.insertList(boAuthTbDeptList);
    }

    @Override
    public void updateService(BoAuthTbDept boAuthTbDept) {
        boAuthTbDept.setUpdateTime(DateUtil.now());
        int reuslt = authTbDeptMapper.updateByPrimaryKeySelective(boAuthTbDept);
        if (reuslt != 1) {
            throw new OperateException("新增失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        authTbDeptMapper.deleteByIdList(ids);
    }
}