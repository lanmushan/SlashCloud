package com.lanmushan.framework.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.*;

public class JedisUtil {
    private Logger log = LoggerFactory.getLogger(getClass());
    private static int expireTime = 1800;
    private  JedisPool jedisPool;

    public JedisUtil(JedisPool jedisPool) {
       this.jedisPool=jedisPool;
    }

    public JedisUtil() {
    }

    public  Jedis getJedis(){
        return jedisPool.getResource();


    }

    public  void  closeJedis(Jedis jedis){
        if (jedis != null)
        {
            jedis.close();
        }
    }
    public  void setObject(String key, Object obj){
        Jedis jedis = getJedis();
        String result = "";
        if(jedis != null){
            try{
                if(!jedis.exists(key.getBytes())){
                    jedis.set(key.getBytes(), getBytes(obj));
                }
                // redis中session过期时间
                jedis.expire(key, expireTime);
            } catch(Exception e){
                e.printStackTrace();
            } finally{
                closeJedis(jedis);
            }
        }
    }
    public static byte[] getBytes(Object obj) {
        try {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bs);
            oos.writeObject(obj);
            byte[] buf = bs.toByteArray();
            oos.flush();
            return buf;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getObject(byte[] bs) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bs);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getObject(String key){
        Jedis jedis = getJedis();
        byte[] bytes = null;
        Object obj=null;
        if(jedis != null){
            try{
                bytes = jedis.get(key.getBytes());
                obj=getObject(bytes);
            }catch(Exception e){
                e.printStackTrace();
            } finally{
                closeJedis(jedis);
            }
        }
        return obj;

    }
}

