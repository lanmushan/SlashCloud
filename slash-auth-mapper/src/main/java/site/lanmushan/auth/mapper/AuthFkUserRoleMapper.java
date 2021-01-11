package site.lanmushan.auth.mapper;


import site.lanmushan.auth.api.entity.AuthFkUserRole;
import site.lanmushan.query.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;

/**
 * 用户角色关系表(AuthFkUserRole)表数据库访问层
 *
 * @author dy
 * @since 2020-07-13 21:33:05
 */
@org.apache.ibatis.annotations.Mapper 
public interface AuthFkUserRoleMapper extends QueryMapper<AuthFkUserRole>, IdListMapper<AuthFkUserRole,Long>, InsertListMapper<AuthFkUserRole> {


}