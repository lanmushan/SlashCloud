package site.lanmushan.framework.configure.datascope;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import site.lanmushan.framework.constant.GlobalInstructionConstant;
import site.lanmushan.framework.datasope.bean.RuleBean;
import site.lanmushan.framework.datasope.support.DataScopeConditionProvider;
import site.lanmushan.framework.datasope.support.DataScopeParameterProvider;
import site.lanmushan.framework.redis.GlobalInstructionEntity;
import site.lanmushan.framework.redis.publish.GlobalInstructionPublish;
import site.lanmushan.framework.redis.subscribe.GlobalInstructionHandler;
import site.lanmushan.framework.redis.subscribe.GlobalInstructionSubscription;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.*;


/**
 * @author Administrator
 */
@Configuration
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataScopeConfig implements GlobalInstructionHandler, ApplicationRunner {
    @Autowired
    DataScopeConditionProvider dataScopeConditionProvider;
    @Autowired
    GlobalInstructionPublish globalInstructionPublish;
    @Autowired
    GlobalInstructionSubscription globalInstructionSubscription;
    @Value("${spring.application.name}")
    private String appName;
    @Bean
    DataScopeParameterProvider dataScopeParameterProvider() {
        return new SlashDataScopeParameterProvider();
    }
    @Override
    public void doInstructionExecute(GlobalInstructionEntity instruction) {
        List<RuleBean> list= JSON.parseArray(instruction.getData().toString(),RuleBean.class);
        list.forEach(it->{
            dataScopeConditionProvider.registerRule(it.getTableName(),it.getRuleMethod(),it);
        });
        log.info("成功注册数据权限{}个",list.size());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        globalInstructionSubscription.registerGlobalInstructionHandler(GlobalInstructionConstant.UPDATE_DATA_SCOPE,appName,this);
        GlobalInstructionEntity globalInstructionEntity=new GlobalInstructionEntity();
        globalInstructionEntity.setCmd(GlobalInstructionConstant.GET_DATA_SCOPE);
        globalInstructionEntity.setData(appName);
        globalInstructionPublish.publish(globalInstructionEntity);
        log.info("发送获取指令");
    }
}
