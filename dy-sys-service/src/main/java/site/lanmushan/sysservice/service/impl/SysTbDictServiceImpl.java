package site.lanmushan.sysservice.service.impl;


import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.sysservice.mapper.SysTbDictMapper;
import site.lanmushan.sysservice.service.SysTbDictService;
import site.lanmushan.framework.utils.OperateException;
import site.lanmushan.sysservice.bo.BoSysTbDict;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.util.date.DateUtil;
import site.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * (SysTbDict)表服务实现类
 *
 * @author dy
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