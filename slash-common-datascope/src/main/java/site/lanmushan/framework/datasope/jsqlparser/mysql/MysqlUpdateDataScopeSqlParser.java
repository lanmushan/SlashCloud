package site.lanmushan.framework.datasope.jsqlparser.mysql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.update.Update;
import site.lanmushan.framework.datasope.bus.DataScopeBus;
import site.lanmushan.framework.datasope.constant.DataScopeMethodConstant;
import site.lanmushan.framework.datasope.jsqlparser.AbstractDataScopeSqlParser;
import site.lanmushan.framework.datasope.jsqlparser.support.IUpdateDataScopeSqlParser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class MysqlUpdateDataScopeSqlParser extends AbstractDataScopeSqlParser implements IUpdateDataScopeSqlParser {
    @Override
    public String parseSql(Statement statement) throws JSQLParserException {

        Map<String, String> tableMap = new HashMap<>(4);
        Update update = (Update) statement;
        FromItem fromItem = update.getTable();
        if (fromItem instanceof Table) {
            Table table = (Table) fromItem;
            if (table.getAlias() != null) {
                tableMap.put(table.getName(), table.getAlias().getName());
            } else {
                tableMap.put(table.getName(), table.getName());
            }
        }
        if (DataScopeBus.isEnable()) {
            Expression dataScopeExpression = getSelectDataScopeCondition(tableMap, DataScopeMethodConstant.DELETE);
            if (dataScopeExpression != null) {
                if (update.getWhere() != null) {
                    Expression oldExpression = update.getWhere();
                    Expression newExpression = new AndExpression(
                            dataScopeExpression,
                            CCJSqlParserUtil.parseCondExpression("(" + oldExpression.toString() + ")")
                    );
                    update.setWhere(newExpression);
                } else {
                    update.setWhere(dataScopeExpression);
                }
            }
        }
        //如果更新语句不携带条件，那么不准更新
        if (update.getWhere() == null) {
            update.setWhere(CCJSqlParserUtil.parseCondExpression("(1!=1)"));
        }
        return statement.toString();
    }


    public static void main(String[] args) {
        String sql = "UPDATE auth_tb_role SET role_code=1 WHERE 1=1";
        try {
            Statement statement = CCJSqlParserUtil.parse(sql);
            MysqlUpdateDataScopeSqlParser mysqlUpdateDataScopeSqlParser = new MysqlUpdateDataScopeSqlParser();
            String result = mysqlUpdateDataScopeSqlParser.parseSql(statement);
            System.out.println(result);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
    }

}
