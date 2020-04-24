package com.lanmushan.dysysservice.service.impl;

import com.lanmushan.dysysservice.bo.SysDictGroupBO;
import com.lanmushan.dysysservice.entity.SysDictGroup;
import com.lanmushan.dysysservice.mapper.SysDictGroupMapper;
import com.lanmushan.dysysservice.service.SysDictGroupService;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.util.date.DateUtil;
import com.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class SysDictGroupServiceImpl implements SysDictGroupService {
    @Autowired
    SysDictGroupMapper sysDictGroupMapper;
    @Override
    public List<SysDictGroup> selectList(QueryInfo queryInfo) {
      return   sysDictGroupMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(SysDictGroupBO bSysDictGroup) {
         sysDictGroupMapper.insertSelective(bSysDictGroup);
    }

    @Override
    public void insertServiceList(List<SysDictGroupBO> bSysDictGroupList) {
      Date now= DateUtil.now();
      bSysDictGroupList.forEach(it->{
          it.setCreateTime(now);
          it.setUpdateTime(now);
          it.setId(MyUUID.getInstance().nextId());
      });
       sysDictGroupMapper.insertList(bSysDictGroupList);
    }

    @Override
    public void updateService(SysDictGroupBO bSysDictGroup) {

    }

    @Override
    public void deleteServiceByIds(Long ids) {

    }
}
