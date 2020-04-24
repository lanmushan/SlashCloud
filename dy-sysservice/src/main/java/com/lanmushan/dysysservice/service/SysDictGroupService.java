package com.lanmushan.dysysservice.service;

import com.lanmushan.dysysservice.bo.SysDictGroupBO;
import com.lanmushan.dysysservice.entity.SysDictGroup;
import com.lanmushan.framework.service.BaseService;

import java.util.List;

/**
 * @author Administrator
 */
public interface SysDictGroupService  extends BaseService<SysDictGroup> {
    /**
     * 新增字典分组
     * @param bSysDictGroup
     * @return
     */
    public void insertService(SysDictGroupBO bSysDictGroup);

    /**
     * 批量新增
     * @param bSysDictGroupList
     * @return
     */
    public void insertServiceList(List<SysDictGroupBO> bSysDictGroupList);

    /**
     * 更新
     * @param bSysDictGroup
     * @return
     */
    public void updateService(SysDictGroupBO bSysDictGroup);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public void deleteServiceByIds(Long ids);
}
