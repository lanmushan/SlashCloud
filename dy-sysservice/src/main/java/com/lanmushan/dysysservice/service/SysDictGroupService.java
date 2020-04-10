package com.lanmushan.dysysservice.service;

import com.lanmushan.dysysservice.bo.BSysDictGroup;
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
    public int insertService(BSysDictGroup bSysDictGroup);

    /**
     * 批量新增
     * @param bSysDictGroupList
     * @return
     */
    public int insertServiceList(List<BSysDictGroup> bSysDictGroupList);

    /**
     * 更新
     * @param bSysDictGroup
     * @return
     */
    public int updateService(BSysDictGroup bSysDictGroup);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public int deleteServiceByIds(Long ids);
}
