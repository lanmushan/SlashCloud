package site.lanmushan.sys.mapper;

import site.lanmushan.framework.query.mapper.QueryMapper;
import site.lanmushan.sys.api.entity.SysTbDictGroup;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * (SysTbDictGroup)表数据库访问层
 *
 * @author dy
 * @since 2020-06-14 21:15:05
 */
@org.apache.ibatis.annotations.Mapper
public interface SysTbDictGroupMapper extends QueryMapper<SysTbDictGroup>, IdListMapper<SysTbDictGroup, Long>, InsertListMapper<SysTbDictGroup> {


}