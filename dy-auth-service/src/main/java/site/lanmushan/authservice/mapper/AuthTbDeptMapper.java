package site.lanmushan.authservice.mapper;

import site.lanmushan.authservice.entity.AuthTbDept;
import site.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 部门表(AuthTbDept)表数据库访问层
 *
 * @author dy
 * @since 2020-07-02 22:14:52
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthTbDeptMapper extends QueryMapper<AuthTbDept>, IdListMapper<AuthTbDept, Long>, InsertListMapper<AuthTbDept> {


}