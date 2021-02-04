package site.lanmushan.cms.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lanmushan.cms.api.bo.BoCmsTbDatasourceType;
import site.lanmushan.cms.mapper.CmsTbDatasourceTypeMapper;
import site.lanmushan.cms.service.CmsTbDatasourceTypeService;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.query.util.TreeUtil;
import site.lanmushan.framework.util.utils.DateUtil;
import site.lanmushan.framework.uuid.MyUUID;

import java.util.Date;
import java.util.List;


/**
 * (CmsTbDatasourceType)表服务实现类
 *
 * @author dy
 * @since 2021-01-30 14:59:53
 */
@Service("cmsTbDatasourceTypeService")
public class CmsTbDatasourceTypeServiceImpl implements CmsTbDatasourceTypeService {
    @Autowired
    private CmsTbDatasourceTypeMapper cmsTbDatasourceTypeMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return cmsTbDatasourceTypeMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoCmsTbDatasourceType boCmsTbDatasourceType) {
        Date now = DateUtil.now();
        boCmsTbDatasourceType.setCreateTime(now);
        boCmsTbDatasourceType.setUpdateTime(now);
        cmsTbDatasourceTypeMapper.insertSelective(boCmsTbDatasourceType);
    }

    @Override
    public void insertServiceList(List<BoCmsTbDatasourceType> boCmsTbDatasourceTypeList) {
        Date now = DateUtil.now();
        boCmsTbDatasourceTypeList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        cmsTbDatasourceTypeMapper.insertList(boCmsTbDatasourceTypeList);
    }

    @Override
    public void updateService(BoCmsTbDatasourceType boCmsTbDatasourceType) {
        boCmsTbDatasourceType.setUpdateTime(DateUtil.now());
        int reuslt = cmsTbDatasourceTypeMapper.updateByPrimaryKeySelective(boCmsTbDatasourceType);
        if (reuslt != 1) {
            throw new OperateException("修改失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        cmsTbDatasourceTypeMapper.deleteByIdList(ids);
    }
    @Override
    public List selectTreeList(QueryInfo queryInfo) {
        List categoryList = cmsTbDatasourceTypeMapper.selectList(queryInfo);
        List resultList= TreeUtil.listToTree(categoryList);
        return resultList;
    }
}