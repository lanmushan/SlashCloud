package site.lanmushan.auth.mapper;

import site.lanmushan.auth.api.entity.AuthTbDatascope;
import site.lanmushan.framework.query.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;

/**
 * 用户表(AuthTbDatascope)表数据库访问层
 *
 * @author dy
 * @since 2021-03-07 16:20:54
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthTbDatascopeMapper extends QueryMapper<AuthTbDatascope>{

}