package site.lanmushan.framework.configure;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.*;

/**
 * @Author dy
 * @Date 2020/6/25 16:25
 * @Version 1.0
 */
public class RedisClient {
    private JedisPool jedisPool;
    private static int expireTime = 200;

    public RedisClient(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static byte[] getBytes(Object obj) {
        try {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bs);
            oos.writeObject(obj);
            byte[] buf = bs.toByteArray();
            oos.flush();
            return buf;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setObject(String key, Object obj) {
        Jedis jedis = getJedis();
        String result = "";
        if (jedis != null) {
            try {
                if (!jedis.exists(key.getBytes())) {
                    jedis.set(key.getBytes(), getBytes(obj));
                }
                // redis中session过期时间
                jedis.expire(key, expireTime);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeJedis(jedis);
            }
        }
    }

    public static Object getObject(byte[] bs) {

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bs);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getObject(String key) {
        Jedis jedis = getJedis();
        byte[] bytes = null;
        Object obj = null;
        if (jedis != null) {
            try {
                bytes = jedis.get(key.getBytes());
                if (bytes == null || bytes.length == 0) {
                    return null;
                }
                obj = getObject(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeJedis(jedis);
            }
        }
        return obj;

    }

    public void deleteObject(String key) {
        Jedis jedis = getJedis();
        try {
            jedis.del(key.getBytes());
            closeJedis(jedis);
        } catch (Exception e) {
            closeJedis(jedis);
        }
    }

    public void closeJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
