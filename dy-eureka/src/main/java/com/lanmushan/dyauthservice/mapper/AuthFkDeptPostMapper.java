package com.lanmushan.dyauthservice.mapper;

import com.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import com.lanmushan.dyauthservice.entity.AuthFkDeptPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色和部门关联表(AuthFkDeptPost)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-15 21:54:28
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthFkDeptPostMapper extends QueryMapper<AuthFkDeptPost>, IdListMapper<AuthFkDeptPost, Long>, InsertListMapper<AuthFkDeptPost> {


}