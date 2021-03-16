package site.lanmushan.auth.mapper;

import site.lanmushan.auth.api.entity.AuthFkRoleResource;
import site.lanmushan.framework.query.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;

/**
 * (AuthFkRoleResource)表数据库访问层
 *
 * @author dy
 * @see QueryMapper#selectQueryById(QueryInfo)
 * @since 2020-06-15 22:13:47
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthFkRoleResourceMapper extends QueryMapper<AuthFkRoleResource> {


}