package com.lanmushan.dysysservice.service.impl;

import com.lanmushan.dysysservice.bo.BSysDictGroup;
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
    public int insertService(BSysDictGroup bSysDictGroup) {
      return   sysDictGroupMapper.insertSelective(bSysDictGroup);
    }

    @Override
    public int insertServiceList(List<BSysDictGroup> bSysDictGroupList) {
      Date now= DateUtil.now();
      bSysDictGroupList.forEach(it->{
          it.setCreateTime(now);
          it.setUpdateTime(now);
          it.setId(MyUUID.getInstance().nextId());
      });
      return sysDictGroupMapper.insertList(bSysDictGroupList);
    }

    @Override
    public int updateService(BSysDictGroup bSysDictGroup) {
        return 0;
    }

    @Override
    public int deleteServiceByIds(Long ids) {
        return 0;
    }
}
