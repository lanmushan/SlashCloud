package site.lanmushan.auth.mapper;


import site.lanmushan.auth.api.entity.AuthTbUserLoginLog;
import site.lanmushan.framework.query.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;

/**
 * 用户登录记录(AuthTbUserLoginLog)表数据库访问层
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthTbUserLoginLogMapper extends QueryMapper<AuthTbUserLoginLog>, IdListMapper<AuthTbUserLoginLog, Long>, InsertListMapper<AuthTbUserLoginLog> {


}