package com.lanmushan.authservice.mapper;

import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import com.lanmushan.authservice.entity.AuthFkRoleResource;

/**
 * (AuthFkRoleResource)表数据库访问层
 *
 * @see com.lanmushan.framework.mapper.QueryMapper#selectQueryById(QueryInfo)
 * @author daiyu
 * @since 2020-06-15 22:13:47
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthFkRoleResourceMapper extends QueryMapper<AuthFkRoleResource>, IdListMapper<AuthFkRoleResource, Long>, InsertListMapper<AuthFkRoleResource> {


}