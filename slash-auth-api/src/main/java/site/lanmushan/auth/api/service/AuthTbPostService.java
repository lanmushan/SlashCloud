package site.lanmushan.auth.api.service;

import site.lanmushan.auth.api.bo.BoAuthTbPost;
import site.lanmushan.auth.api.entity.AuthTbPost;
import site.lanmushan.framework.query.service.BaseService;

import java.util.List;

/**
 * 岗位信息表(AuthTbPost)表服务接口
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
public interface AuthTbPostService extends BaseService<AuthTbPost> {


    /**
     * 新增数据
     *
     * @param authTbPost 实例对象
     * @return 实例对象
     */
    void insertService(BoAuthTbPost authTbPost);

    /**
     * 修改数据
     *
     * @param authTbPost 实例对象
     * @return 实例对象
     */
    void updateService(BoAuthTbPost authTbPost);

    /**
     * 批量新增
     *
     * @param boAuthTbPostList 实例对象
     * @return
     */
    void insertServiceList(List<BoAuthTbPost> boAuthTbPostList);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteServiceByIds(List<Long> ids);


}