package com.lanmushan.authservice.service.impl;


import com.lanmushan.authservice.mapper.AuthTbPostMapper;
import com.lanmushan.authservice.service.AuthTbPostService;
import com.lanmushan.framework.constant.HTTPCode;
import com.lanmushan.framework.exception.OperateException;
import com.lanmushan.authservice.bo.BoAuthTbPost;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.util.date.DateUtil;
import com.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 岗位信息表(AuthTbPost)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@Service("authTbPostService")
public class AuthTbPostServiceImpl implements AuthTbPostService {
    @Autowired
    private AuthTbPostMapper authTbPostMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return authTbPostMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoAuthTbPost boAuthTbPost) {
        Date now = DateUtil.now();
        boAuthTbPost.setCreateTime(now);
        boAuthTbPost.setUpdateTime(now);
        authTbPostMapper.insertSelective(boAuthTbPost);
    }

    @Override
    public void insertServiceList(List<BoAuthTbPost> boAuthTbPostList) {
        Date now = DateUtil.now();
        boAuthTbPostList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        authTbPostMapper.insertList(boAuthTbPostList);
    }

    @Override
    public void updateService(BoAuthTbPost boAuthTbPost) {
        boAuthTbPost.setUpdateTime(DateUtil.now());
        int reuslt = authTbPostMapper.updateByPrimaryKeySelective(boAuthTbPost);
        if (reuslt != 1) {
            throw new OperateException("新增失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        authTbPostMapper.deleteByIdList(ids);
    }
}