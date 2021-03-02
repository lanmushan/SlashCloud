package site.lanmushan.framework.redis.publish;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import site.lanmushan.framework.redis.GlobalInstructionEntity;
import site.lanmushan.framework.redis.RedisClientService;
import site.lanmushan.framework.redis.subscribe.GlobalInstructionSubscription;

/**
 * @author Administrator
 */
public class GlobalInstructionPublish {

    public GlobalInstructionPublish(RedisClientService redisClientService) {
        this.redisClientService = redisClientService;
    }

    RedisClientService redisClientService;

    /**
     * 发布全局指令
     *
     * @param globalInstructionEntity
     */
    public void publish(GlobalInstructionEntity globalInstructionEntity) {
        Jedis jedis = null;
        try {
            jedis = redisClientService.getResource();
            jedis.publish(GlobalInstructionSubscription.REDIS_GLOBAL_SUBSCRIPTION_INSTRUCTION, JSON.toJSONString(globalInstructionEntity));

        } catch (Exception e) {

        } finally {
            if (jedis != null) {
                redisClientService.close(jedis);
            }
        }
    }
}
