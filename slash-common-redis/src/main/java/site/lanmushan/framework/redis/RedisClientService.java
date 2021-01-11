package site.lanmushan.framework.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisClientService {
    private JedisPool jedisPool;

    public RedisClientService(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public RedisClientService() {
    }

    public void hset(String hash, String key, Object object) {

    }

    public void hget(String hash, String key, Object object) {

    }

    public Jedis getResource() {
        synchronized (jedisPool) {
            return jedisPool.getResource();
        }

    }

    public void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}