package site.lanmushan.sys.mapper;


import site.lanmushan.framework.query.mapper.QueryMapper;
import site.lanmushan.sys.api.entity.SysTbDict;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * (SysTbDict)表数据库访问层
 *
 * @author dy
 * @since 2020-06-14 21:15:03
 */
@org.apache.ibatis.annotations.Mapper 
public interface SysTbDictMapper extends QueryMapper<SysTbDict>{


}