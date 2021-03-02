package site.lanmushan.cms.mapper;

import site.lanmushan.cms.api.entity.CmsTbRequestMappingDatasource;
import site.lanmushan.framework.query.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * (CmsTbRequestMappingDatasource)表数据库访问层
 *
 * @author dy
 * @since 2021-02-08 20:22:29
 */
@org.apache.ibatis.annotations.Mapper
public interface CmsTbRequestMappingDatasourceMapper extends QueryMapper<CmsTbRequestMappingDatasource>, InsertListMapper<CmsTbRequestMappingDatasource>, IdListMapper<CmsTbRequestMappingDatasource, Long> {


}