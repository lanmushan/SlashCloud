package site.lanmushan.framework.mapper;

import tk.mybatis.mapper.common.Mapper;

/**
 * mapper基类
 * @author Administrator
 */

public interface QueryMapper<T> extends SelectQueryMapper<T>, Mapper<T> {


}
