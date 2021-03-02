package site.lanmushan.framework.configure;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import site.lanmushan.framework.redis.RedisClientService;

/**
 * @Author dy
 * @Date 2020/6/25 16:28
 * @Version 1.0
 */
@Configuration
@Slf4j
public class RedisClientServiceConfig {
    private JedisPool jedisPool;
    @Value("${spring.redis.port}")
    int port;
    @Value("${spring.redis.host}")
    String host;

    @Bean
    public RedisClientService redisClient() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMinIdle(5);
        jedisPoolConfig.setMaxWaitMillis(500);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(300);
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(10000);
        jedisPoolConfig.setTestWhileIdle(false);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPool = new JedisPool(jedisPoolConfig, host, port);
        RedisClientService redisClientService = new RedisClientService(jedisPool);
        log.info("redisClientService配置成功");
        return redisClientService;
    }
}
