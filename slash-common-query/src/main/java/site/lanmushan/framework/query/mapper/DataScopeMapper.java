package site.lanmushan.framework.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.lanmushan.framework.dto.DHashMap;
import site.lanmushan.framework.query.entity.BaseEntity;


import java.util.List;
/**
 * @author Administrator
 */
@Mapper
public interface DataScopeMapper {
    List<DHashMap> selectAllTable();
    List<DHashMap> selectFieldByTableName(@Param("tableName") String tableName);

}
