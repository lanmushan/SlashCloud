package site.lanmushan.sysservice.mapper;

import site.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import site.lanmushan.sysservice.entity.SysTbDict;

/**
 * (SysTbDict)表数据库访问层
 *
 * @author dy
 * @since 2020-06-14 21:15:03
 */
@org.apache.ibatis.annotations.Mapper 
public interface SysTbDictMapper extends QueryMapper<SysTbDict>, IdListMapper<SysTbDict,Long>, InsertListMapper<SysTbDict>{


}