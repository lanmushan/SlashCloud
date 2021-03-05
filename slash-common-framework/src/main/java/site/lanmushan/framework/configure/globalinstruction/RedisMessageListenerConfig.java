package site.lanmushan.framework.configure.globalinstruction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import site.lanmushan.framework.redis.subscribe.GlobalInstructionSubscription;
/**
 * @author Administrator
 */
@Configuration
public class RedisMessageListenerConfig {
    @Autowired
    GlobalInstructionSubscription globalInstructionSubscription;
    @Bean
    RedisMessageListenerContainer container(JedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(globalInstructionSubscription, new ChannelTopic(GlobalInstructionSubscription.REDIS_GLOBAL_SUBSCRIPTION_INSTRUCTION));
        return container;
    }


}
