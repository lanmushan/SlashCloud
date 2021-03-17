package site.lanmushan.framework.datasope.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import site.lanmushan.framework.datasope.bus.DataScopeBus;
import site.lanmushan.framework.datasope.constant.DataScopeMethodConstant;
import site.lanmushan.framework.datasope.jsqlparser.support.IDataScopeHandler;
import site.lanmushan.framework.util.ApplicationUtil;

import java.util.Properties;

/**
 *
 * @author Administrator
 */

@Slf4j
@Intercepts
        ({
                @Signature(type = Executor.class, method = "query", args = {
                        MappedStatement.class,
                        Object.class,
                        RowBounds.class,
                        ResultHandler.class}),
                @Signature(type = Executor.class, method = "update", args = {
                        MappedStatement.class,
                        Object.class})
        })
public class QueryMybatisInterceptor implements Interceptor {
    // 这是对应上面的args的序号
    static int MAPPED_STATEMENT_INDEX = 0;
    static int PARAMETER_INDEX = 1;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] queryArgs = invocation.getArgs();
        final Object parameter = queryArgs[PARAMETER_INDEX];
        final MappedStatement mappedStatement = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        final BoundSql boundSql = mappedStatement.getBoundSql(parameter);

        String typeName = mappedStatement.getSqlCommandType().name().toLowerCase();

        if (DataScopeBus.isEnable()||typeName.equals(DataScopeMethodConstant.UPDATE)||typeName.equals(DataScopeMethodConstant.DELETE)) {
            long start = System.currentTimeMillis();
            String resultSql = ApplicationUtil.getBean(IDataScopeHandler.class).parseSql(boundSql.getSql(), typeName.toLowerCase());
            long end = System.currentTimeMillis();
            log.info("转换SQL耗时:{}毫秒 结果:{}", end - start, resultSql);
            // 重新new一个查询语句对像
            BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), resultSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            //这里要兼容pageHelper
            BoundSqlSqlSource staticSqlSource = new BoundSqlSqlSource(newBoundSql);
            MappedStatement newMappedStatement = copyFromMappedStatement(mappedStatement, staticSqlSource);
            for (ParameterMapping mapping : boundSql.getParameterMappings()) {
                String prop = mapping.getProperty();
                if (boundSql.hasAdditionalParameter(prop)) {
                    newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
                }
            }
            queryArgs[MAPPED_STATEMENT_INDEX] = newMappedStatement;
            return invocation.proceed();

        }
        return invocation.proceed();

    }


    @Override
    public Object plugin(Object target) {
        if (target instanceof CachingExecutor) {
            Executor executor = (Executor) target;
            Plugin.wrap(target, this);
        } else if (target instanceof ParameterHandler) {
            ParameterHandler parameterHandler = (ParameterHandler) target;

        } else if (target instanceof ResultSetHandler) {
            ResultSetHandler resultSetHandler = (ResultSetHandler) target;
        } else if (target instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) target;
            BoundSql boundSql = statementHandler.getBoundSql();
        }
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
            builder.keyProperty(ms.getKeyProperties()[0]);
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

}
