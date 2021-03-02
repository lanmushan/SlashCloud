package site.lanmushan.framework.redis;

import org.omg.CORBA.PUBLIC_MEMBER;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisClient {
    private JedisPool jedisPool;

    public RedisClient(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public RedisClient() {
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

