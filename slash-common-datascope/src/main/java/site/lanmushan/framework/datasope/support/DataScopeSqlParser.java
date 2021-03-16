package site.lanmushan.framework.datasope.support;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;

import java.util.Map;
import net.sf.jsqlparser.statement.Statement;

/**
 * @author Administrator
 */
public interface DataScopeSqlParser {


    public String parseSql(Statement statement) throws JSQLParserException;
    /**
     * 获取条件
     * @param tableMap
     * @return
     * @throws JSQLParserException
     */
    Expression getSelectDataScopeCondition(Map<String, String> tableMap,String method) throws JSQLParserException;
}
