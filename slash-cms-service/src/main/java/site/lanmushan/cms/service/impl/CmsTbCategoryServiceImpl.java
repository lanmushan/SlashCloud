package site.lanmushan.cms.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lanmushan.cms.api.bo.BoCmsTbCategory;
import site.lanmushan.cms.api.service.CmsTbCategoryService;
import site.lanmushan.cms.mapper.CmsTbCategoryMapper;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.query.util.TreeUtil;
import site.lanmushan.framework.util.utils.DateUtil;
import site.lanmushan.framework.uuid.MyUUID;

import java.util.Date;
import java.util.List;


/**
 * (CmsTbCategory)表服务实现类
 *
 * @author dy
 * @since 2021-01-30 14:59:51
 */
@Service("cmsTbCategoryService")
public class CmsTbCategoryServiceImpl implements CmsTbCategoryService {
//    @Autowired
    private CmsTbCategoryMapper cmsTbCategoryMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return cmsTbCategoryMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoCmsTbCategory boCmsTbCategory) {
        Date now = DateUtil.now();
        boCmsTbCategory.setCreateTime(now);
        boCmsTbCategory.setUpdateTime(now);
        cmsTbCategoryMapper.insertSelective(boCmsTbCategory);
    }

    @Override
    public void insertServiceList(List<BoCmsTbCategory> boCmsTbCategoryList) {
        Date now = DateUtil.now();
        boCmsTbCategoryList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        cmsTbCategoryMapper.insertList(boCmsTbCategoryList);
    }

    @Override
    public void updateService(BoCmsTbCategory boCmsTbCategory) {
        boCmsTbCategory.setUpdateTime(DateUtil.now());
        int reuslt = cmsTbCategoryMapper.updateByPrimaryKeySelective(boCmsTbCategory);
        if (reuslt != 1) {
            throw new OperateException("修改失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        cmsTbCategoryMapper.deleteByIdList(ids);
    }

    @Override
    public List selectTreeList(QueryInfo queryInfo) {
        List categoryList = cmsTbCategoryMapper.selectList(queryInfo);
        return TreeUtil.listToTree(categoryList);
    }
}