package site.lanmushan.framework.redis.publish;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import site.lanmushan.framework.redis.GlobalInstructionEntity;
import site.lanmushan.framework.redis.subscribe.GlobalInstructionSubscription;

/**
 * @author Administrator
 */
@Slf4j
@Component
public class GlobalInstructionPublish {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 发布全局指令
     *
     * @param globalInstructionEntity
     */
    public void publish(GlobalInstructionEntity globalInstructionEntity) {
        redisTemplate.convertAndSend(GlobalInstructionSubscription.REDIS_GLOBAL_SUBSCRIPTION_INSTRUCTION, JSONObject.toJSONString(globalInstructionEntity));

    }
}
