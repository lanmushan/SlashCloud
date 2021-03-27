package site.lanmushan.auth.mapper;

import org.apache.ibatis.annotations.Param;
import site.lanmushan.auth.api.entity.AuthTbRole;
import site.lanmushan.framework.query.mapper.QueryMapper;

import java.util.List;

/**
 * 角色表(AuthTbRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthTbRoleMapper extends QueryMapper<AuthTbRole> {
    /**
     * 根据用户ID查询所有的关联角色
     *
     * @param userId
     * @return
     */
    List<AuthTbRole> selectRolesByUserId(Long userId);

    /**
     * 根据资源id查询相关角色
     * @param id
     * @return
     */
    List<AuthTbRole> selectRolesByResourceId(@Param("id") Long id);
}