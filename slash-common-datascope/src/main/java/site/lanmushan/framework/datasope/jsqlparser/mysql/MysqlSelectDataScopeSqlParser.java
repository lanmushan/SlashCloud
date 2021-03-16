package site.lanmushan.framework.datasope.jsqlparser.mysql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import site.lanmushan.framework.datasope.constant.DataScopeMethodConstant;
import site.lanmushan.framework.datasope.jsqlparser.AbstractDataScopeSqlParser;
import site.lanmushan.framework.datasope.jsqlparser.support.ISelectDataScopeSqlParser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class MysqlSelectDataScopeSqlParser extends AbstractDataScopeSqlParser implements ISelectDataScopeSqlParser {
    @Override
    public String parseSql(Statement statement) throws JSQLParserException {
        Select select = (Select) statement;
        SelectBody selectBody = select.getSelectBody();
        PlainSelect plainSelect = (PlainSelect) selectBody;

        doPlainSelect(plainSelect);
        return statement.toString();
    }




    private void doPlainSelect(PlainSelect plainSelect) throws JSQLParserException {
        //一般不超过4个表
        Map<String, String> tableMap = new HashMap<>(4);
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            Table table = (Table) plainSelect.getFromItem();
            if (table.getAlias() != null) {
                tableMap.put(table.getName(), table.getAlias().getName());
            } else {
                tableMap.put(table.getName(), table.getName());
            }
        } else if (fromItem instanceof SubSelect) {
            SubSelect subSelect = (SubSelect) fromItem;
            // subSelect.getAlias(); //获取别名
            if (subSelect.getSelectBody() != null && subSelect.getSelectBody() instanceof PlainSelect) {
                doPlainSelect((PlainSelect) subSelect.getSelectBody());
            }
        }
        if (plainSelect.getJoins() != null) {
            for (Join join : plainSelect.getJoins()) {
                Table joinTable = (Table) join.getRightItem();
                if (joinTable.getAlias() != null) {
                    tableMap.put(joinTable.getName(), joinTable.getAlias().getName());
                } else {
                    tableMap.put(joinTable.getName(), joinTable.getName());
                }
            }
        }
        Expression dataScopeExpression = getSelectDataScopeCondition(tableMap, DataScopeMethodConstant.SELECT);
        if (dataScopeExpression != null) {
            if (plainSelect.getWhere() != null) {
                Expression oldExpression = plainSelect.getWhere();
                Expression newExpression = new AndExpression(
                        dataScopeExpression,
                        CCJSqlParserUtil.parseCondExpression("(" + oldExpression.toString() + ")")
                );
                plainSelect.setWhere(newExpression);
            } else {
                plainSelect.setWhere(dataScopeExpression);
            }
        }

    }


}
