package com.lanmushan.sysservice.service.impl;


import com.lanmushan.sysservice.mapper.SysTbDictGroupMapper;
import com.lanmushan.sysservice.service.SysTbDictGroupService;
import com.lanmushan.framework.constant.HTTPCode;
import com.lanmushan.framework.exception.OperateException;
import com.lanmushan.sysservice.bo.BoSysTbDictGroup;
import com.lanmushan.sysservice.entity.SysTbDictGroup;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.util.date.DateUtil;
import com.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;



/**
 * (SysTbDictGroup)表服务实现类
 *
 * @author makejava
 * @since 2020-06-14 21:15:06
 */
@Service("sysTbDictGroupService")
public class SysTbDictGroupServiceImpl implements SysTbDictGroupService {
     @Autowired
    private SysTbDictGroupMapper sysTbDictGroupMapper;
      @Override
    public List selectList(QueryInfo queryInfo) {
      return  sysTbDictGroupMapper.selectList(queryInfo);
    }
    @Override
    public void insertService(BoSysTbDictGroup boSysTbDictGroup) {
         Date now= DateUtil.now();
         boSysTbDictGroup.setCreateTime(now);
         boSysTbDictGroup.setUpdateTime(now);
         sysTbDictGroupMapper.insertSelective(boSysTbDictGroup);
    }
      @Override
    public  void insertServiceList(List<BoSysTbDictGroup> boSysTbDictGroupList) {
      Date now= DateUtil.now();
      boSysTbDictGroupList.forEach(it->{
          it.setCreateTime(now);
          it.setUpdateTime(now);
          it.setId(MyUUID.getInstance().nextId());
      });
        sysTbDictGroupMapper.insertList(boSysTbDictGroupList);
    }
        @Override
    public void updateService(BoSysTbDictGroup boSysTbDictGroup) {
        boSysTbDictGroup.setUpdateTime(DateUtil.now());
        int reuslt= sysTbDictGroupMapper.updateByPrimaryKeySelective(boSysTbDictGroup);
        if(reuslt!=1)
        {
            throw new OperateException("新增失败", HTTPCode.Fail);
        }
    }
        @Override
    public void deleteServiceByIds(List<Long> ids) {
        sysTbDictGroupMapper.deleteByIdList(ids);
    }
}