package site.lanmushan.framework.configure.datascope;

import com.alibaba.fastjson.JSONObject;
import site.lanmushan.framework.authorization.CurrentUser;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.datasope.bean.DataScopeUserContext;
import site.lanmushan.framework.datasope.support.DataScopeParameterProvider;

/**
 * @author Administrator
 */
public class SlashDataScopeParameterProvider implements DataScopeParameterProvider {

    @Override
    public DataScopeUserContext getDataScopeParameter() {
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser();
        JSONObject json = (JSONObject) JSONObject.toJSON(currentUser);
        DataScopeUserContext context= json.toJavaObject(DataScopeUserContext.class);
        return context;
    }
}
