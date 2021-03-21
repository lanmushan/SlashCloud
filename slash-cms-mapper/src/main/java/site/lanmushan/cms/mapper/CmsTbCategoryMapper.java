package site.lanmushan.cms.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.lanmushan.cms.api.entity.CmsTbCategory;
import site.lanmushan.framework.query.mapper.QueryMapper;

@Mapper
public interface CmsTbCategoryMapper  extends QueryMapper<CmsTbCategory> {

}
