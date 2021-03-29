package site.lanmushan.cms.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.lanmushan.cms.api.entity.CmsTbCategory;
import site.lanmushan.framework.query.mapper.QueryMapper;

/**
 * @author Administrator
 */
@Mapper
public interface CmsTbCategoryMapper extends QueryMapper<CmsTbCategory> {
}
