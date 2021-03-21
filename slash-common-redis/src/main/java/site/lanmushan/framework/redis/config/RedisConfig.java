package site.lanmushan.framework.redis.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @Author dy
 * @Date 2020/6/25 16:28
 * @Version 1.0
 */
@Configuration
@Slf4j
public class RedisConfig {
    @Value("${spring.redis.host:127.0.0.1}")
    String host;
    private JedisPool jedisPool;
    @Value("${spring.redis.port:6379}")
    int port;
    @Value("${spring.redis.password}")
    private String pwd;
    @Value("${spring.redis.sentinel.master:}")
    private String master;
    @Value("${spring.redis.sentinel.nodes:}")
    private String nodes;
    @Value("${spring.redis.database:0}")
    private int dbIndex;
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMinIdle(5);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(300);
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(10000);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(3600);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        // jedisPoolConfig.setMinEvictableIdleTimeMillis(1800000);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(3);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(-1);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(true);
        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(false);
        return jedisPoolConfig;
    }
    @Bean
    @ConditionalOnProperty(value = "spring.redis.mode",havingValue = "sentinel")
    public JedisConnectionFactory redisSentinelConfigurationFactory() {
        String[] split = nodes.split(",");
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        for (int i = 0; i < split.length; i++) {
            String[] node = split[i].split(":");
            RedisNode redisNode = new RedisNode(node[0], Integer.parseInt(node[1]));
            redisNode.setName(master);
            redisSentinelConfiguration.addSentinel(redisNode);
        }
        redisSentinelConfiguration.setDatabase(0);
        redisSentinelConfiguration.setMaster(master);
        redisSentinelConfiguration.setPassword(RedisPassword.of(pwd));
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration);
        //TODO :重要
        jedisConnectionFactory.afterPropertiesSet();

        return jedisConnectionFactory;
    }
    @Bean
    @ConditionalOnProperty(value = "spring.redis.mode",havingValue = "standalone",matchIfMissing=true)
    public JedisConnectionFactory redisStandaloneRedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(dbIndex);
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(pwd));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }


}
