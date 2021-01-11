package site.lanmushan.auth.service;



import site.lanmushan.auth.api.bo.BoAuthTbRole;
import site.lanmushan.auth.api.entity.AuthTbRole;
import site.lanmushan.query.service.BaseService;

import java.util.List;

/**
 * 角色表(AuthTbRole)表服务接口
 *
 * @author dy
 * @since 2020-07-13 21:28:55
 */
public interface AuthTbRoleService extends BaseService<AuthTbRole> {


    /**
     * 新增数据
     *
     * @param authTbRole 实例对象
     * @return 实例对象
     */
    void insertService(BoAuthTbRole authTbRole);

    /**
     * 修改数据
     *
     * @param authTbRole 实例对象
     * @return 实例对象
     */
    void updateService(BoAuthTbRole authTbRole);
    
        /**
     * 批量新增
     * @param  boAuthTbRoleList 实例对象
     * @return
     */
      void insertServiceList(List<BoAuthTbRole> boAuthTbRoleList);

      /**
     * 批量删除
     * @param ids
     * @return
     */
      void deleteServiceByIds(List<Long> ids);


}