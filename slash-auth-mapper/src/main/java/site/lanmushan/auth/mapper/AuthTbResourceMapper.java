package site.lanmushan.auth.mapper;

import org.apache.ibatis.annotations.Param;
import site.lanmushan.auth.api.entity.AuthTbResource;
import site.lanmushan.auth.api.vo.VoAuthTbResource;
import site.lanmushan.auth.api.vo.VoUrlRoles;
import site.lanmushan.framework.query.mapper.QueryMapper;

import java.util.List;

/**
 * 菜单表(AuthTbResource)表数据库访问层
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthTbResourceMapper extends QueryMapper<AuthTbResource> {

    /**
     * 根据角色查询资源
     *
     * @param roleCodes
     * @param type
     * @return
     */
    List<VoAuthTbResource> selectResourceByRoleCodes(@Param("roleCodes") String roleCodes, @Param("type") String type);

    /**
     * 根据url查询关联的角色信息
     *
     * @param url
     * @return
     */
    List<VoUrlRoles> selectApiRelationRoles(@Param("url") String url);

    List<VoAuthTbResource> selectResourceByRoleCode(@Param("roleCode") String roleCode,@Param("resourceType") String resourceType);
}