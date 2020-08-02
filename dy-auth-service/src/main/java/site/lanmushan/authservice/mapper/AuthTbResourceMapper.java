package site.lanmushan.authservice.mapper;

import site.lanmushan.authservice.entity.AuthTbResource;
import site.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单表(AuthTbResource)表数据库访问层
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthTbResourceMapper extends QueryMapper<AuthTbResource>, IdListMapper<AuthTbResource, Long>, InsertListMapper<AuthTbResource> {

    /**
     * 根据角色查询资源
     *
     * @param roleCodes
     * @param type
     * @return
     */
    List<AuthTbResource> selectResourceByRoleCodes(@Param("roleCodes") String roleCodes, @Param("type") String type);
}