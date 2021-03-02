package site.lanmushan.cms.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lanmushan.cms.api.bo.BoCmsTbRequestMappingDatasource;
import site.lanmushan.cms.mapper.CmsTbRequestMappingDatasourceMapper;
import site.lanmushan.cms.service.CmsTbRequestMappingDatasourceService;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.util.utils.DateUtil;
import site.lanmushan.framework.uuid.MyUUID;

import java.util.Date;
import java.util.List;


/**
 * (CmsTbRequestMappingDatasource)表服务实现类
 *
 * @author dy
 * @since 2021-02-08 20:35:00
 */
@Service("cmsTbRequestMappingDatasourceService")
public class CmsTbRequestMappingDatasourceServiceImpl implements CmsTbRequestMappingDatasourceService {
    @Autowired
    private CmsTbRequestMappingDatasourceMapper cmsTbRequestMappingDatasourceMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return cmsTbRequestMappingDatasourceMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoCmsTbRequestMappingDatasource boCmsTbRequestMappingDatasource) {
        Date now = DateUtil.now();
        boCmsTbRequestMappingDatasource.setCreateTime(now);
        boCmsTbRequestMappingDatasource.setUpdateTime(now);
        cmsTbRequestMappingDatasourceMapper.insertSelective(boCmsTbRequestMappingDatasource);
    }

    @Override
    public void insertServiceList(List<BoCmsTbRequestMappingDatasource> boCmsTbRequestMappingDatasourceList) {
        Date now = DateUtil.now();
        boCmsTbRequestMappingDatasourceList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        cmsTbRequestMappingDatasourceMapper.insertList(boCmsTbRequestMappingDatasourceList);
    }

    @Override
    public void updateService(BoCmsTbRequestMappingDatasource boCmsTbRequestMappingDatasource) {
        boCmsTbRequestMappingDatasource.setUpdateTime(DateUtil.now());
        int reuslt = cmsTbRequestMappingDatasourceMapper.updateByPrimaryKeySelective(boCmsTbRequestMappingDatasource);
        if (reuslt != 1) {
            throw new OperateException("修改失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        cmsTbRequestMappingDatasourceMapper.deleteByIdList(ids);
    }
}