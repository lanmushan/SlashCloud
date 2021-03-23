package site.lanmushan.framework.datasope.support;

import site.lanmushan.framework.datasope.bean.DataScopeUserContext;
import site.lanmushan.framework.datasope.bean.RuleBean;

import java.util.List;

/**
 * @author Administrator
 */
public interface DataScopeConditionProvider {
    List<RuleBean> selectRuleList(String tableName, String method);

    void registerRuleList(String tableName, String method, List<RuleBean> ruleBeanList);
    void registerRule(String tableName,String method,RuleBean bean);
    void removeRuleList(String tableName, String method);

    public String getCondition(String tableName, String method, DataScopeUserContext context);
}
