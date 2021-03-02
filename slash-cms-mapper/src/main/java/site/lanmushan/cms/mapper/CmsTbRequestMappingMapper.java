package site.lanmushan.cms.mapper;

import site.lanmushan.cms.api.entity.CmsTbRequestMapping;
import site.lanmushan.framework.query.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * (CmsTbRequestMapping)表数据库访问层
 *
 * @author dy
 * @since 2021-02-07 20:41:10
 */
@org.apache.ibatis.annotations.Mapper
public interface CmsTbRequestMappingMapper extends QueryMapper<CmsTbRequestMapping>, InsertListMapper<CmsTbRequestMapping>, IdListMapper<CmsTbRequestMapping, Long> {


}