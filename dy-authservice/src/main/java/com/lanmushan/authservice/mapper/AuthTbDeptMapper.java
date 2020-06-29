package com.lanmushan.authservice.mapper;

import com.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import com.lanmushan.authservice.entity.AuthTbDept;

/**
 * 部门表(AuthTbDept)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthTbDeptMapper extends QueryMapper<AuthTbDept>, IdListMapper<AuthTbDept, Long>, InsertListMapper<AuthTbDept> {


}