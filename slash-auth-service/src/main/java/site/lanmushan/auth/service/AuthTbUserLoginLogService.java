package site.lanmushan.auth.service;


import site.lanmushan.auth.api.bo.BoAuthTbUserLoginLog;
import site.lanmushan.auth.api.entity.AuthTbUserLoginLog;
import site.lanmushan.query.service.BaseService;

import java.util.List;

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