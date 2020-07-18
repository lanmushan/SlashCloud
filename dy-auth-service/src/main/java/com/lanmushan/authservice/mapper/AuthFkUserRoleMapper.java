package com.lanmushan.authservice.mapper;
import com.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import com.lanmushan.authservice.entity.AuthFkUserRole;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户角色关系表(AuthFkUserRole)表数据库访问层
 *
 * @author daiyu
 * @since 2020-07-13 21:33:05
 */
@org.apache.ibatis.annotations.Mapper 
public interface AuthFkUserRoleMapper extends QueryMapper<AuthFkUserRole>, IdListMapper<AuthFkUserRole,Long>, InsertListMapper<AuthFkUserRole>{


}