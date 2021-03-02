package site.lanmushan.framework.query.mapper;

import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * mapper基类
 *
 * @author Administrator
 */
public interface QueryMapper<T> extends SelectQueryMapper<T>, Mapper<T>, InsertListMapper<T>, IdListMapper<T, Long> {


}
