package site.lanmushan.framework.datasope.jsqlparser;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import site.lanmushan.framework.datasope.bean.DataScopeUserContext;
import site.lanmushan.framework.datasope.bus.DataScopeBus;
import site.lanmushan.framework.datasope.support.DataScopeConditionProvider;
import site.lanmushan.framework.datasope.support.DataScopeParameterProvider;
import site.lanmushan.framework.datasope.support.DataScopeSqlParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Data
@Slf4j
public abstract class AbstractDataScopeSqlParser implements DataScopeSqlParser {
    @Autowired
    private DataScopeConditionProvider dataScopeConditionProvider;
    @Autowired
    private DataScopeParameterProvider dataScopeParameterProvider;


    @Override
    public Expression getSelectDataScopeCondition(Map<String, String> tableMap, String method) throws JSQLParserException {
        DataScopeUserContext userContext = dataScopeParameterProvider.getDataScopeParameter();

        if (tableMap == null || tableMap.size() == 0) {
            return null;
        }
        List<String> conditionList = new ArrayList<>(tableMap.size());

        tableMap.forEach((key, value) -> {
            if (!DataScopeBus.isExcludeTable(key)) {
                if (DataScopeBus.isEnableTable(key)) {
                    String alias = value;
                    String condition = dataScopeConditionProvider.getCondition(key, method, userContext);
                    if (condition != null) {
                        condition = condition.replaceAll("#", alias + ".");
                        conditionList.add(condition);
                    }
                }
            }

        });
        if (conditionList.size() == 0) {
            return null;
        }
        String fullCondition = String.join(" and ", conditionList);
        return CCJSqlParserUtil.parseCondExpression("(" + fullCondition + ")");
    }
}
