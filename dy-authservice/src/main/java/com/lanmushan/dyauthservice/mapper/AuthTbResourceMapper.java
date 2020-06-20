package com.lanmushan.dyauthservice.mapper;

import com.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import com.lanmushan.dyauthservice.entity.AuthTbResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单表(AuthTbResource)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthTbResourceMapper extends QueryMapper<AuthTbResource>, IdListMapper<AuthTbResource, Long>, InsertListMapper<AuthTbResource> {

    /**
     * 根据角色查询资源
     *
     * @param roleIds
     * @param type
     * @return
     */
    List<AuthTbResource> selectResourceByRoleIds(@Param("roleIds") String roleIds, @Param("type") String type);
}