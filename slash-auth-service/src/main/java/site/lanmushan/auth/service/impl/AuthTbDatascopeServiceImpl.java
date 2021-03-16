package site.lanmushan.auth.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lanmushan.auth.api.bo.BoAuthTbDatascope;
import site.lanmushan.auth.api.entity.AuthTbDatascope;
import site.lanmushan.auth.mapper.AuthTbDatascopeMapper;
import site.lanmushan.auth.api.service.AuthTbDatascopeService;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.util.utils.DateUtil;
import site.lanmushan.framework.uuid.MyUUID;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;


/**
 * 用户表(AuthTbDatascope)表服务实现类
 *
 * @author dy
 * @since 2021-03-07 16:25:52
 */
@Service("authTbDatascopeService")
public class AuthTbDatascopeServiceImpl implements AuthTbDatascopeService {
    @Autowired
    private AuthTbDatascopeMapper authTbDatascopeMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return authTbDatascopeMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoAuthTbDatascope boAuthTbDatascope) {
        Date now = DateUtil.now();
        boAuthTbDatascope.setCreateTime(now);
        boAuthTbDatascope.setUpdateTime(now);
        authTbDatascopeMapper.insertSelective(boAuthTbDatascope);
    }
    @Transactional
    @Override
    public void insertServiceList(List<BoAuthTbDatascope> boAuthTbDatascopeList) {
        Date now = DateUtil.now();
        if(boAuthTbDatascopeList.size()==0){
            return;
        }
        Example example = new Example(AuthTbDatascope.class);
        example.createCriteria().andEqualTo("appName",boAuthTbDatascopeList.get(0).getAppName())
                .andEqualTo("ruleType",boAuthTbDatascopeList.get(0).getRuleType())
                .andEqualTo("tableName",boAuthTbDatascopeList.get(0).getTableName());
        authTbDatascopeMapper.deleteByExample(example);
        boAuthTbDatascopeList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        authTbDatascopeMapper.insertList(boAuthTbDatascopeList);
    }

    @Override
    public void updateService(BoAuthTbDatascope boAuthTbDatascope) {
        boAuthTbDatascope.setUpdateTime(DateUtil.now());
        int reuslt = authTbDatascopeMapper.updateByPrimaryKeySelective(boAuthTbDatascope);
        if (reuslt != 1) {
            throw new OperateException("修改失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        authTbDatascopeMapper.deleteByIdList(ids);
    }
}