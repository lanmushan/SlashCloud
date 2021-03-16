package site.lanmushan.framework.datasope.interceptor;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.session.Configuration;

import java.util.List;

/**
 * @author Administrator
 */
public class BoundSqlSqlSource implements SqlSource {
    private BoundSql sql;

    public BoundSqlSqlSource(BoundSql sql) {

        this.sql=sql;
    }


    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return sql;
    }
}
