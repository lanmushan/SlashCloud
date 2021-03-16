package site.lanmushan.framework.redis.subscribe;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import site.lanmushan.framework.redis.GlobalInstructionEntity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 全局指令订阅，用于资源跨系统更新情况
 *
 * @author Administrator
 */
@Slf4j
@Component
public class GlobalInstructionSubscription implements MessageListener {
    public static final String REDIS_GLOBAL_SUBSCRIPTION_INSTRUCTION = "redis_global_subscription_instruction";
    private volatile List<GlobalInstructionSubscriptionMapping> globalInstructionSubscriptionMappingList = new CopyOnWriteArrayList<GlobalInstructionSubscriptionMapping>();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String jsonStr = new String(message.getBody());
        GlobalInstructionEntity globalInstructionEntity = JSON.parseObject(jsonStr, GlobalInstructionEntity.class);
        if (globalInstructionEntity == null || globalInstructionEntity.getCmd() == null) {
            return;
        }
        globalInstructionSubscriptionMappingList.forEach(it -> {
            try {
                //完全匹配
                if (it.getCmd().equals(globalInstructionEntity.getCmd())&&it.getType()!=null&&it.getType().equals(globalInstructionEntity.getType())) {
                    it.getGlobalInstructionHander().doInstructionExecute(globalInstructionEntity);
                }else if(it.getCmd().equals(globalInstructionEntity.getCmd()))
                {
                    it.getGlobalInstructionHander().doInstructionExecute(globalInstructionEntity);

                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
    }


    /**
     * 注册指令处理器
     *
     * @param cmd
     * @param type
     * @param globalInstructionHander
     */
    public void registerGlobalInstructionHandler(String cmd, String type, GlobalInstructionHandler globalInstructionHander) {
        if (cmd == null) {
            throw new RuntimeException("订阅的指令不能为空");
        }
        GlobalInstructionSubscriptionMapping mapping = new GlobalInstructionSubscriptionMapping();
        mapping.setCmd(cmd);
        mapping.setType(type);
        mapping.setGlobalInstructionHander(globalInstructionHander);
        globalInstructionSubscriptionMappingList.add(mapping);
    }


}

@Data
class GlobalInstructionSubscriptionMapping {
    private String cmd;
    private String type;
    private GlobalInstructionHandler globalInstructionHander;
}