package site.lanmushan.framework.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import site.lanmushan.framework.redis.subscribe.GlobalInstructionSubscription;
import site.lanmushan.framework.redis.RedisClientService;

/**
 * @author Administrator
 */
@Configuration
@Slf4j
@ConditionalOnBean(value = GlobalInstructionSubscription.class)
public class GlobalInstructionSubscriptionRunner implements ApplicationRunner {
    @Autowired
    RedisClientService redisClientService;
    @Autowired
    GlobalInstructionSubscription globalInstructionSubscription;

    @Override
    public void run(ApplicationArguments args) {
        redisClientService.getResource().subscribe(globalInstructionSubscription, GlobalInstructionSubscription.REDIS_GLOBAL_SUBSCRIPTION_INSTRUCTION);
    }
}
