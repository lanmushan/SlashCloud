package site.lanmushan.auth.service;

import site.lanmushan.auth.api.bo.BoAuthTbUser;
import site.lanmushan.auth.api.entity.AuthTbUser;
import site.lanmushan.framework.query.service.BaseService;

import java.util.List;

/**
 * 用户表(AuthTbUser)表服务接口
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
public interface AuthTbUserService extends BaseService<AuthTbUser> {


    /**
     * 新增数据
     *
     * @param authTbUser 实例对象
     * @return 实例对象
     */
    void insertService(BoAuthTbUser authTbUser);

    /**
     * 修改数据
     *
     * @param authTbUser 实例对象
     * @return 实例对象
     */
    void updateService(BoAuthTbUser authTbUser);

    /**
     * 批量新增
     *
     * @param boAuthTbUserList 实例对象
     * @return
     */
    void insertServiceList(List<BoAuthTbUser> boAuthTbUserList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);

    /**
     * 重置指定用户密码
     *
     * @param userId
     * @param password
     */
    void resetLoginPassword(Long userId, String password);
}