package site.lanmushan.framework.redis.subscribe;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPubSub;
import site.lanmushan.framework.redis.GlobalInstructionEntity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 全局指令订阅，用于资源跨系统更新情况
 *
 * @author Administrator
 */
@Slf4j
public class GlobalInstructionSubscription extends JedisPubSub {
    public static final String REDIS_GLOBAL_SUBSCRIPTION_INSTRUCTION = "redis_global_subscription_instruction";
    private volatile List<GlobalInstructionSubscriptionMapping> globalInstructionHanderList = new CopyOnWriteArrayList<GlobalInstructionSubscriptionMapping>();

    @Override
    public void onMessage(String channel, String message) {
        log.info(message);
        GlobalInstructionEntity globalInstructionEntity = JSON.parseObject(message, GlobalInstructionEntity.class);
        if (globalInstructionEntity == null || globalInstructionEntity.getCmd() == null) {
            return;
        }
        globalInstructionHanderList.forEach(it -> {
            try {
                //完全匹配
                if (it.getCmd().equals(globalInstructionEntity.getCmd())) {
                    it.getGlobalInstructionHander().doInstructionExecute(globalInstructionEntity);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {    //订阅了频道会调用
        log.info("订阅成功..");

    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {   //取消订阅 会调用
        log.info("取消全局订阅成功..");

    }

    /**
     * 注册指令处理器
     *
     * @param cmd
     * @param type
     * @param globalInstructionHander
     */
    public void registerGlobalInstructionHander(String cmd, String type, GlobalInstructionHander globalInstructionHander) {
        if (cmd == null) {
            throw new RuntimeException("订阅的指令不能为空");
        }
        GlobalInstructionSubscriptionMapping mapping = new GlobalInstructionSubscriptionMapping();
        mapping.setCmd(cmd);
        mapping.setType(type);
        mapping.setGlobalInstructionHander(globalInstructionHander);
        globalInstructionHanderList.add(mapping);
    }
}

@Data
class GlobalInstructionSubscriptionMapping {
    private String cmd;
    private String type;
    private GlobalInstructionHander globalInstructionHander;
}