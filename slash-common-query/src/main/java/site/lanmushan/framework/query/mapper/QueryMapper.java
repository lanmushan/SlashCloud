package site.lanmushan.framework.query.mapper;


import site.lanmushan.framework.query.annotations.ConditionDisable;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;

/**
 * mapper基类
 *
 * @author Administrator
 */
@ConditionDisable
public interface QueryMapper<T> extends SelectQueryMapper<T>, Mapper<T>, InsertListMapper<T>, IdListMapper<T, Long> {


}
