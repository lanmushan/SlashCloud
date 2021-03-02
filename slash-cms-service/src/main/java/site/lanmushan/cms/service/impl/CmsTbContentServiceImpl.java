package site.lanmushan.cms.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lanmushan.cms.api.bo.BoCmsTbContent;
import site.lanmushan.cms.mapper.CmsTbContentMapper;
import site.lanmushan.cms.service.CmsTbContentService;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.util.utils.DateUtil;
import site.lanmushan.framework.uuid.MyUUID;

import java.util.Date;
import java.util.List;


/**
 * 文章(CmsTbContent)表服务实现类
 *
 * @author dy
 * @since 2021-01-30 14:59:53
 */
@Service("cmsTbContentService")
public class CmsTbContentServiceImpl implements CmsTbContentService {
    @Autowired
    private CmsTbContentMapper cmsTbContentMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return cmsTbContentMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoCmsTbContent boCmsTbContent) {
        Date now = DateUtil.now();
        boCmsTbContent.setCreateTime(now);
        boCmsTbContent.setUpdateTime(now);
        boCmsTbContent.setContentHit(0);
        boCmsTbContent.setContentDisplay(1);
        cmsTbContentMapper.insertSelective(boCmsTbContent);
    }

    @Override
    public void insertServiceList(List<BoCmsTbContent> boCmsTbContentList) {
        Date now = DateUtil.now();
        boCmsTbContentList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        cmsTbContentMapper.insertList(boCmsTbContentList);
    }

    @Override
    public void updateService(BoCmsTbContent boCmsTbContent) {
        boCmsTbContent.setUpdateTime(DateUtil.now());
        int reuslt = cmsTbContentMapper.updateByPrimaryKeySelective(boCmsTbContent);
        if (reuslt != 1) {
            throw new OperateException("修改失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        cmsTbContentMapper.deleteByIdList(ids);
    }
}