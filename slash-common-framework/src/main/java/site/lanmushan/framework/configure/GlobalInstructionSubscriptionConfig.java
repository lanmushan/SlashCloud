package site.lanmushan.framework.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.lanmushan.framework.redis.RedisClientService;
import site.lanmushan.framework.redis.publish.GlobalInstructionPublish;
import site.lanmushan.framework.redis.subscribe.GlobalInstructionSubscription;

/**
 * @author Administrator
 */
@Configuration
public class GlobalInstructionSubscriptionConfig {
    @Autowired
    RedisClientService redisClientService;

    @Bean
    public GlobalInstructionSubscription globalInstructionSubscription() {
        return new GlobalInstructionSubscription();
    }

    @Bean
    public GlobalInstructionPublish globalInstructionPublish() {
        return new GlobalInstructionPublish(redisClientService);
    }
}
