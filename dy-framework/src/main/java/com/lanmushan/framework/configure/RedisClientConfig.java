package com.lanmushan.framework.configure;

import com.lanmushan.framework.configure.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author dy
 * @Date 2020/6/25 16:28
 * @Version 1.0
 */
@Configuration
public class RedisClientConfig {
    private Logger log = LoggerFactory.getLogger(getClass());
    private JedisPool jedisPool;
    @Value("${spring.redis.port}")
    int port;
    @Value("${spring.redis.host}")
    String host;

    @Bean
    public RedisClient redisClient() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);//设置最大连接数
        jedisPoolConfig.setMaxIdle(100);//设置最大空闲数
        jedisPoolConfig.setMinIdle(5);
        jedisPoolConfig.setMaxWaitMillis(1000);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(300);
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(10000);
        jedisPoolConfig.setTestWhileIdle(false);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPool = new JedisPool(jedisPoolConfig, host, port);
        return new RedisClient(jedisPool);
    }
}
