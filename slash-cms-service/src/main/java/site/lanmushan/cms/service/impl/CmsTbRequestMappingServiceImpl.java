package site.lanmushan.cms.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lanmushan.cms.api.bo.BoCmsTbRequestMapping;
import site.lanmushan.cms.api.entity.CmsTbRequestMapping;
import site.lanmushan.cms.api.entity.CmsTbRequestMappingDatasource;
import site.lanmushan.cms.mapper.CmsTbRequestMappingDatasourceMapper;
import site.lanmushan.cms.mapper.CmsTbRequestMappingMapper;
import site.lanmushan.cms.service.CmsTbRequestMappingService;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.util.utils.DateUtil;
import site.lanmushan.framework.uuid.MyUUID;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;


/**
 * (CmsTbRequestMapping)表服务实现类
 *
 * @author dy
 * @since 2021-02-07 20:45:37
 */
@Service("cmsTbRequestMappingService")
public class CmsTbRequestMappingServiceImpl implements CmsTbRequestMappingService {
    @Autowired
    private CmsTbRequestMappingMapper cmsTbRequestMappingMapper;
    @Autowired
    private CmsTbRequestMappingDatasourceMapper cmsTbRequestMappingDatasourceMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return cmsTbRequestMappingMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoCmsTbRequestMapping boCmsTbRequestMapping) {
        Date now = DateUtil.now();
        boCmsTbRequestMapping.setCreateTime(now);
        boCmsTbRequestMapping.setUpdateTime(now);
        cmsTbRequestMappingMapper.insertSelective(boCmsTbRequestMapping);
    }

    @Override
    public void insertServiceList(List<BoCmsTbRequestMapping> boCmsTbRequestMappingList) {
        Date now = DateUtil.now();
        boCmsTbRequestMappingList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        cmsTbRequestMappingMapper.insertList(boCmsTbRequestMappingList);
    }

    @Override
    public void updateService(BoCmsTbRequestMapping boCmsTbRequestMapping) {
        CmsTbRequestMapping mapping = cmsTbRequestMappingMapper.selectByPrimaryKey(boCmsTbRequestMapping.getId());
        boCmsTbRequestMapping.setUpdateTime(DateUtil.now());
        Example example = new Example(CmsTbRequestMappingDatasource.class);
        example.createCriteria().andEqualTo("fkRequestUrl", mapping.getRequestUrl());
        CmsTbRequestMappingDatasource cmsTbRequestMappingDatasource = new CmsTbRequestMappingDatasource();
        cmsTbRequestMappingDatasource.setFkRequestUrl(boCmsTbRequestMapping.getRequestUrl());
        cmsTbRequestMappingDatasourceMapper.updateByExampleSelective(cmsTbRequestMappingDatasource, example);
        int reuslt = cmsTbRequestMappingMapper.updateByPrimaryKeySelective(boCmsTbRequestMapping);
        if (reuslt != 1) {
            throw new OperateException("修改失败", HTTPCode.Fail);
        }

    }

    @Transactional
    @Override
    public void deleteServiceByIds(List<Long> ids) {
        ids.forEach(it -> {
            CmsTbRequestMapping cmsTbRequestMapping = cmsTbRequestMappingMapper.selectByPrimaryKey(it);
            cmsTbRequestMapping.getRequestUrl();
            Example example = new Example(CmsTbRequestMappingDatasource.class);
            example.createCriteria().andEqualTo("fkRequestUrl", cmsTbRequestMapping.getRequestUrl());
            int result = cmsTbRequestMappingDatasourceMapper.deleteByExample(example);
            System.out.println(result);
        });
        cmsTbRequestMappingMapper.deleteByIdList(ids);
    }
}