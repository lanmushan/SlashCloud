package site.lanmushan.authservice.mapper;

import site.lanmushan.authservice.entity.AuthTbRole;
import site.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

/**
 * 角色表(AuthTbRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthTbRoleMapper extends QueryMapper<AuthTbRole>, IdListMapper<AuthTbRole, Long>, InsertListMapper<AuthTbRole> {
    /**
     * 根据用户ID查询所有的关联角色
     *
     * @param userId
     * @return
     */
    List<AuthTbRole> selectRolesByUserId(Long userId);
}