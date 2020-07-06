package com.lanmushan.authservice.mapper;

import com.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import com.lanmushan.authservice.entity.AuthTbPost;

/**
 * 岗位信息表(AuthTbPost)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthTbPostMapper extends QueryMapper<AuthTbPost>, IdListMapper<AuthTbPost, Long>, InsertListMapper<AuthTbPost> {


}