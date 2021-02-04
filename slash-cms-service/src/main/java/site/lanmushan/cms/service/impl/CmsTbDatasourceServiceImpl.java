package site.lanmushan.cms.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lanmushan.cms.api.bo.BoCmsTbDatasource;
import site.lanmushan.cms.mapper.CmsTbDatasourceMapper;
import site.lanmushan.cms.service.CmsTbDatasourceService;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.util.utils.DateUtil;
import site.lanmushan.framework.uuid.MyUUID;

import java.util.Date;
import java.util.List;


/**
 * (CmsTbDatasource)表服务实现类
 *
 * @author dy
 * @since 2021-01-30 14:59:53
 */
@Service("cmsTbDatasourceService")
public class CmsTbDatasourceServiceImpl implements CmsTbDatasourceService {
    @Autowired
    private CmsTbDatasourceMapper cmsTbDatasourceMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return cmsTbDatasourceMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoCmsTbDatasource boCmsTbDatasource) {
        Date now = DateUtil.now();
        boCmsTbDatasource.setCreateTime(now);
        boCmsTbDatasource.setUpdateTime(now);
        cmsTbDatasourceMapper.insertSelective(boCmsTbDatasource);
    }

    @Override
    public void insertServiceList(List<BoCmsTbDatasource> boCmsTbDatasourceList) {
        Date now = DateUtil.now();
        boCmsTbDatasourceList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        cmsTbDatasourceMapper.insertList(boCmsTbDatasourceList);
    }

    @Override
    public void updateService(BoCmsTbDatasource boCmsTbDatasource) {
        boCmsTbDatasource.setUpdateTime(DateUtil.now());
        int reuslt = cmsTbDatasourceMapper.updateByPrimaryKeySelective(boCmsTbDatasource);
        if (reuslt != 1) {
            throw new OperateException("修改失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        cmsTbDatasourceMapper.deleteByIdList(ids);
    }
}