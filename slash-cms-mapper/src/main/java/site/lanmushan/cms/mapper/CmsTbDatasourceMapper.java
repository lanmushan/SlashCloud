package site.lanmushan.cms.mapper;

import org.apache.ibatis.annotations.Param;
import site.lanmushan.cms.api.entity.CmsTbDatasource;
import site.lanmushan.framework.query.mapper.QueryMapper;

import java.util.HashMap;
import java.util.List;


/**
 * (CmsTbDatasource)表数据库访问层
 *
 * @author dy
 * @since 2021-01-30 14:54:17
 */
@org.apache.ibatis.annotations.Mapper
public interface CmsTbDatasourceMapper extends QueryMapper<CmsTbDatasource> {

    List<HashMap> executeSql(@Param("sql") String sql);
}