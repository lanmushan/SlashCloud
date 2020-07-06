package com.lanmushan.sysservice.mapper;
import com.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import com.lanmushan.sysservice.entity.SysTbDict;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SysTbDict)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-14 21:15:03
 */
@org.apache.ibatis.annotations.Mapper 
public interface SysTbDictMapper extends QueryMapper<SysTbDict>, IdListMapper<SysTbDict,Long>, InsertListMapper<SysTbDict>{


}