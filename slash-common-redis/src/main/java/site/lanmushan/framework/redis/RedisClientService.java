package site.lanmushan.framework.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Administrator
 */
public class RedisClientService {
    private JedisPool jedisPool;

    public RedisClientService(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public RedisClientService() {
    }

    /**
     * 设置
     *
     * @param key
     * @param expireSeconds
     * @param value
     * @return
     */
    public boolean setex(String key, int expireSeconds, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (null == value) {
                return false;
            }
            if (expireSeconds <= 0) {
                jedis.set(key, value);
            } else {
                jedis.setex(key, expireSeconds, value);
            }
            return true;
        } finally {
            close(jedis);
        }
    }

    /**
     * 判断否个KEY是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (null == key) {
                return false;
            }
            return jedis.exists(key);
        } finally {
            close(jedis);
        }
    }

    /**
     * 获取
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            close(jedis);
        }
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