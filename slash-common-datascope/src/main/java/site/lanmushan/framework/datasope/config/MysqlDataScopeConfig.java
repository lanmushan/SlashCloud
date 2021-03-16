package site.lanmushan.framework.datasope.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import site.lanmushan.framework.datasope.jsqlparser.mysql.*;
import site.lanmushan.framework.datasope.jsqlparser.support.IDataScopeHandler;
import site.lanmushan.framework.datasope.support.DataScopeConditionProvider;

/**
 * @author Administrator
 */
@Configuration
@Import({MysqlSelectDataScopeSqlParser.class,
        MysqlUpdateDataScopeSqlParser.class,
        MysqlDeleteDataScopeSqlParser.class})
public class MysqlDataScopeConfig {

    @Bean
    public IDataScopeHandler dataScopeHandler() {
        MysqlDataScopeHandler mysqlDataScopeHandler = new MysqlDataScopeHandler();
        return mysqlDataScopeHandler;
    }

    @Bean
    public DataScopeConditionProvider dataScopeConditionProvider() {
        return new MysqlDataScopeRuleConditionProvider();
    }

}
