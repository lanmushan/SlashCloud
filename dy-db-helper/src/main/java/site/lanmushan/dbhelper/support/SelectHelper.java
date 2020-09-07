package site.lanmushan.dbhelper.support;

import java.util.List;

public interface SelectHelper {
    /**
     * 根据主键查询返回实体
     * @param entity
     * @param <T>
     * @return
     */
    <T> T selectByPrimaryKey(T entity);

    /**
     * 查询列表
     * @param entity
     * @param <T>
     * @return
     */
    <T> List<T> selectList(T entity);

    /**
     * 查询并且返回一个
     * @param entity
     * @param <T>
     * @return
     */
    <T> T selectOne(T entity);
}
