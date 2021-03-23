package site.lanmushan.framework.datasope.jsqlparser.mysql;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import site.lanmushan.framework.datasope.bean.DataScopeUserContext;
import site.lanmushan.framework.datasope.bean.RuleBean;
import site.lanmushan.framework.datasope.support.DataScopeConditionProvider;
import site.lanmushan.groovyscript.GroovyScriptUtil;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Administrator
 */
@Slf4j
public class MysqlDataScopeRuleConditionProvider implements DataScopeConditionProvider {

    private ConcurrentHashMap<String, List<RuleBean>> cacheMap = new ConcurrentHashMap();

    @Override
    public List<RuleBean> selectRuleList(String tableName, String method) {
        return cacheMap.get(getKey(tableName, method));
    }

    @Override
    public void registerRuleList(String tableName, String method, List<RuleBean> ruleBeanList) {
        cacheMap.put(getKey(tableName, method), ruleBeanList);

    }

    @Override
    public void registerRule(String tableName, String method, RuleBean bean) {
        String key = getKey(tableName, method);
        if (cacheMap.containsKey(getKey(tableName, method))) {
            cacheMap.get(key).add(bean);
        } else {
            cacheMap.put(key, new CopyOnWriteArrayList<RuleBean>());
            cacheMap.get(key).add(bean);
        }
    }

    @Override
    public void removeRuleList(String tableName, String method) {
        cacheMap.remove(getKey(tableName, method));

    }

    private String getKey(String tableName, String method) {
        return tableName + "|" + method;
    }

    @Override
    public String getCondition(String tableName, String method, DataScopeUserContext dataScopeUserContext) {
        JSONObject json = (JSONObject) JSONObject.toJSON(dataScopeUserContext);

        List<RuleBean> list = selectRuleList(tableName, method);
        if (list == null || list.size() == 0) {
            return null;
        }
        String result = "1!=1";
        for (RuleBean rule : list) {
            Boolean success = (Boolean) GroovyScriptUtil.execute(rule.getRuleCondition(),dataScopeUserContext , json.getInnerMap(),Boolean.class);

            if (success) {
                result = GroovyScriptUtil.execute(JSONObject.toJSONString(rule.getRuleValue()),dataScopeUserContext, json.getInnerMap());
                break;
            }
        }
        return result;

    }


}
