package site.lanmushan.authservice.mapper;

import site.lanmushan.authservice.entity.AuthFkDeptPost;
import site.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 角色和部门关联表(AuthFkDeptPost)表数据库访问层
 *
 * @author dy
 * @since 2020-06-15 22:13:47
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthFkDeptPostMapper extends QueryMapper<AuthFkDeptPost>, IdListMapper<AuthFkDeptPost, Long>, InsertListMapper<AuthFkDeptPost> {


}