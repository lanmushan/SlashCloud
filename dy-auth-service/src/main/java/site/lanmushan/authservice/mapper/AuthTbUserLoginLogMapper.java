package site.lanmushan.authservice.mapper;

import site.lanmushan.authservice.entity.AuthTbUserLoginLog;
import site.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 用户登录记录(AuthTbUserLoginLog)表数据库访问层
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthTbUserLoginLogMapper extends QueryMapper<AuthTbUserLoginLog>, IdListMapper<AuthTbUserLoginLog, Long>, InsertListMapper<AuthTbUserLoginLog> {


}