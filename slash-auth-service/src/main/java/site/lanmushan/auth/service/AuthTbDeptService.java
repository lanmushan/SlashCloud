package site.lanmushan.auth.service;

import site.lanmushan.auth.api.bo.BoAuthTbDept;
import site.lanmushan.auth.api.entity.AuthTbDept;
import site.lanmushan.framework.query.service.BaseService;

import java.util.List;

/**
 * 部门表(AuthTbDept)表服务接口
 *
 * @author dy
 * @since 2020-07-02 22:14:53
 */
public interface AuthTbDeptService extends BaseService<AuthTbDept> {


    /**
     * 新增数据
     *
     * @param authTbDept 实例对象
     * @return 实例对象
     */
    void insertService(BoAuthTbDept authTbDept);

    /**
     * 修改数据
     *
     * @param authTbDept 实例对象
     * @return 实例对象
     */
    void updateService(BoAuthTbDept authTbDept);

    /**
     * 批量新增
     * @param  boAuthTbDeptList 实例对象
     * @return
     */
    void insertServiceList(List<BoAuthTbDept> boAuthTbDeptList);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}