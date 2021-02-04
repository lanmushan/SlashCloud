package site.lanmushan.auth.mapper;

import site.lanmushan.auth.api.entity.AuthTbPost;
import site.lanmushan.framework.query.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 岗位信息表(AuthTbPost)表数据库访问层
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthTbPostMapper extends QueryMapper<AuthTbPost>{


}