package site.lanmushan.authservice.service;

import site.lanmushan.authservice.bo.BoAuthTbResource;

import java.util.List;

import site.lanmushan.authservice.entity.AuthTbResource;
import site.lanmushan.framework.service.BaseService;

/**
 * 菜单表(AuthTbResource)表服务接口
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
public interface AuthTbResourceService extends BaseService<AuthTbResource> {


    /**
     * 新增数据
     *
     * @param authTbResource 实例对象
     * @return 实例对象
     */
    void insertService(BoAuthTbResource authTbResource);

    /**
     * 修改数据
     *
     * @param authTbResource 实例对象
     * @return 实例对象
     */
    void updateService(BoAuthTbResource authTbResource);

    /**
     * 批量新增
     *
     * @param boAuthTbResourceList 实例对象
     * @return
     */
    void insertServiceList(List<BoAuthTbResource> boAuthTbResourceList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}