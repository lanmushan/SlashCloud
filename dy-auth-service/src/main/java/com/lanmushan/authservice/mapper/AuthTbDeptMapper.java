package com.lanmushan.authservice.mapper;
import com.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import com.lanmushan.authservice.entity.AuthTbDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门表(AuthTbDept)表数据库访问层
 *
 * @author daiyu
 * @since 2020-07-02 22:14:52
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthTbDeptMapper extends QueryMapper<AuthTbDept>, IdListMapper<AuthTbDept, Long>, InsertListMapper<AuthTbDept> {


}