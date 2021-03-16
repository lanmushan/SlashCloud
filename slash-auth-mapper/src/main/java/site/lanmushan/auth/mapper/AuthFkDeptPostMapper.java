package site.lanmushan.auth.mapper;

import site.lanmushan.auth.api.entity.AuthFkDeptPost;
import site.lanmushan.framework.query.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;

/**
 * 角色和部门关联表(AuthFkDeptPost)表数据库访问层
 *
 * @author dy
 * @since 2020-06-15 22:13:47
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthFkDeptPostMapper extends QueryMapper<AuthFkDeptPost>, IdListMapper<AuthFkDeptPost, Long>, InsertListMapper<AuthFkDeptPost> {


}