package site.lanmushan.auth.mapper;

import org.apache.ibatis.annotations.Param;
import site.lanmushan.auth.api.entity.AuthTbResource;
import site.lanmushan.framework.query.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

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
    List<AuthTbResource> selectResourceByRoleCodes(@Param("roleCodes") String roleCodes, @Param("type") String type);
}