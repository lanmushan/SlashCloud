package site.lanmushan.sysservice.service.impl;


import site.lanmushan.sysservice.mapper.SysTbDictGroupMapper;
import site.lanmushan.sysservice.service.SysTbDictGroupService;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.sysservice.bo.BoSysTbDictGroup;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.util.date.DateUtil;
import site.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * (SysTbDictGroup)表服务实现类
 *
 * @author dy
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