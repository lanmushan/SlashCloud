package site.lanmushan.sys.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.util.utils.DateUtil;
import site.lanmushan.framework.uuid.MyUUID;
import site.lanmushan.sys.api.bo.BoSysTbDictGroup;
import site.lanmushan.sys.mapper.SysTbDictGroupMapper;
import site.lanmushan.sys.api.service.SysTbDictGroupService;

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
        return sysTbDictGroupMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoSysTbDictGroup boSysTbDictGroup) {
        Date now = DateUtil.now();
        boSysTbDictGroup.setCreateTime(now);
        boSysTbDictGroup.setUpdateTime(now);
        boSysTbDictGroup.setAllowEdit(1);
        boSysTbDictGroup.setDeleted(0);
        boSysTbDictGroup.setCreateUserAccount(CurrentUserUtil.getCurrentUser().getAccount());
        sysTbDictGroupMapper.insertSelective(boSysTbDictGroup);
    }

    @Override
    public void insertServiceList(List<BoSysTbDictGroup> boSysTbDictGroupList) {
        Date now = DateUtil.now();
        boSysTbDictGroupList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        sysTbDictGroupMapper.insertList(boSysTbDictGroupList);
    }

    @Override
    public void updateService(BoSysTbDictGroup boSysTbDictGroup) {
        boSysTbDictGroup.setUpdateTime(DateUtil.now());
        int reuslt = sysTbDictGroupMapper.updateByPrimaryKeySelective(boSysTbDictGroup);
        if (reuslt != 1) {
            throw new OperateException("新增失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        sysTbDictGroupMapper.deleteByIdList(ids);
    }
}