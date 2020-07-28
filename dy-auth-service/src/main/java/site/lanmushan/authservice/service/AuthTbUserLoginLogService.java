package site.lanmushan.authservice.service;

import site.lanmushan.authservice.bo.BoAuthTbUserLoginLog;

import java.util.List;

import site.lanmushan.authservice.entity.AuthTbUserLoginLog;
import site.lanmushan.framework.service.BaseService;

/**
 * 用户登录记录(AuthTbUserLoginLog)表服务接口
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
public interface AuthTbUserLoginLogService extends BaseService<AuthTbUserLoginLog> {


    /**
     * 新增数据
     *
     * @param authTbUserLoginLog 实例对象
     * @return 实例对象
     */
    void insertService(BoAuthTbUserLoginLog authTbUserLoginLog);

    /**
     * 修改数据
     *
     * @param authTbUserLoginLog 实例对象
     * @return 实例对象
     */
    void updateService(BoAuthTbUserLoginLog authTbUserLoginLog);

    /**
     * 批量新增
     *
     * @param boAuthTbUserLoginLogList 实例对象
     * @return
     */
    void insertServiceList(List<BoAuthTbUserLoginLog> boAuthTbUserLoginLogList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}