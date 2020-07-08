package com.lanmushan.sysservice.service.impl;


import com.lanmushan.sysservice.mapper.SysTbDictMapper;
import com.lanmushan.sysservice.service.SysTbDictService;
import com.lanmushan.framework.constant.HTTPCode;
import com.lanmushan.framework.exception.OperateException;
import com.lanmushan.sysservice.bo.BoSysTbDict;
import com.lanmushan.sysservice.entity.SysTbDict;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.util.date.DateUtil;
import com.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;



/**
 * (SysTbDict)表服务实现类
 *
 * @author daiyu
 * @since 2020-06-14 21:15:04
 */
@Service("sysTbDictService")
public class SysTbDictServiceImpl implements SysTbDictService {
     @Autowired
    private SysTbDictMapper sysTbDictMapper;
      @Override
    public List selectList(QueryInfo queryInfo) {
      return  sysTbDictMapper.selectList(queryInfo);
    }
    @Override
    public void insertService(BoSysTbDict boSysTbDict) {
         Date now= DateUtil.now();
         boSysTbDict.setCreateTime(now);
         boSysTbDict.setUpdateTime(now);
         sysTbDictMapper.insertSelective(boSysTbDict);
    }
      @Override
    public  void insertServiceList(List<BoSysTbDict> boSysTbDictList) {
      Date now= DateUtil.now();
      boSysTbDictList.forEach(it->{
          it.setCreateTime(now);
          it.setUpdateTime(now);
          it.setId(MyUUID.getInstance().nextId());
      });
        sysTbDictMapper.insertList(boSysTbDictList);
    }
        @Override
    public void updateService(BoSysTbDict boSysTbDict) {
        boSysTbDict.setUpdateTime(DateUtil.now());
        int reuslt= sysTbDictMapper.updateByPrimaryKeySelective(boSysTbDict);
        if(reuslt!=1)
        {
            throw new OperateException("新增失败", HTTPCode.Fail);
        }
    }
        @Override
    public void deleteServiceByIds(List<Long> ids) {
        sysTbDictMapper.deleteByIdList(ids);
    }
}