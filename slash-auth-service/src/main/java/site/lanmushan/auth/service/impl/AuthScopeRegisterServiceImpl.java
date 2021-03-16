package site.lanmushan.auth.service.impl;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import site.lanmushan.auth.api.entity.AuthTbDatascope;
import site.lanmushan.auth.mapper.AuthTbDatascopeMapper;
import site.lanmushan.framework.constant.GlobalInstructionConstant;
import site.lanmushan.framework.datasope.bean.RuleBean;
import site.lanmushan.framework.redis.GlobalInstructionEntity;
import site.lanmushan.framework.redis.publish.GlobalInstructionPublish;
import site.lanmushan.framework.redis.subscribe.GlobalInstructionHandler;
import site.lanmushan.framework.redis.subscribe.GlobalInstructionSubscription;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Service
@Slf4j
@Order(999)
public class AuthScopeRegisterServiceImpl implements GlobalInstructionHandler {
    @Autowired
    AuthTbDatascopeMapper authTbDatascopeMapper;
    @Autowired
    GlobalInstructionSubscription globalInstructionSubscription;
    @Autowired
    GlobalInstructionPublish globalInstructionPublish;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    void init() {
        globalInstructionSubscription.registerGlobalInstructionHandler(GlobalInstructionConstant.GET_DATA_SCOPE, null, this);

    }

    public void doPublish(String appName) {
        GlobalInstructionEntity globalInstructionEntity = new GlobalInstructionEntity();
        globalInstructionEntity.setCmd(GlobalInstructionConstant.UPDATE_DATA_SCOPE);
        AuthTbDatascope query = new AuthTbDatascope();
        query.setAppName(appName);
        List<AuthTbDatascope> authTbDatascopeList = authTbDatascopeMapper.select(query);
        List<RuleBean> ruleBeans=new ArrayList<>(authTbDatascopeList.size());
        authTbDatascopeList.forEach(it->{
            RuleBean ruleBean=new RuleBean();
            ruleBean.setAppName(it.getAppName());
            ruleBean.setRuleCondition(it.getRuleCondition());
            ruleBean.setRulePriority(it.getRulePriority());
            ruleBean.setTableName(it.getTableName());
            ruleBean.setRuleValue(it.getRuleValue());
            ruleBean.setRuleMethod(it.getRuleType());
            ruleBeans.add(ruleBean);
        });
        globalInstructionEntity.setData(ruleBeans);
        globalInstructionEntity.setType(appName);
        globalInstructionPublish.publish(globalInstructionEntity);
    }

    @Override
    public void doInstructionExecute(GlobalInstructionEntity instruction) {
        if (instruction.getData() != null && !"".equals(instruction.toString())) {
            doPublish(instruction.getData().toString());
        }
    }
}
