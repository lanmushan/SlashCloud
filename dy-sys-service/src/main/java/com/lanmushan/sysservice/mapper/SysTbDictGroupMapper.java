package com.lanmushan.sysservice.mapper;
import com.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import com.lanmushan.sysservice.entity.SysTbDictGroup;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SysTbDictGroup)表数据库访问层
 *
 * @author dy
 * @since 2020-06-14 21:15:05
 */
@org.apache.ibatis.annotations.Mapper 
public interface SysTbDictGroupMapper extends QueryMapper<SysTbDictGroup>, IdListMapper<SysTbDictGroup,Long>, InsertListMapper<SysTbDictGroup>{


}