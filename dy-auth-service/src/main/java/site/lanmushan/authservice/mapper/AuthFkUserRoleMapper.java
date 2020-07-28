package site.lanmushan.authservice.mapper;

import site.lanmushan.authservice.entity.AuthFkUserRole;
import site.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 用户角色关系表(AuthFkUserRole)表数据库访问层
 *
 * @author dy
 * @since 2020-07-13 21:33:05
 */
@org.apache.ibatis.annotations.Mapper 
public interface AuthFkUserRoleMapper extends QueryMapper<AuthFkUserRole>, IdListMapper<AuthFkUserRole,Long>, InsertListMapper<AuthFkUserRole>{


}