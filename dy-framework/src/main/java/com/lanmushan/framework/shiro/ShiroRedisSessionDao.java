package com.lanmushan.framework.shiro;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

/**
 * 基于redis分布式session实现
 * @author Administrator
 */
@Configuration
@Order(10)
public class ShiroRedisSessionDao extends CachingSessionDAO{

    @Autowired
    RedisTemplate redisTemplate;

    private static final String PREFIX = "SESSION_ID";

    private static final int EXPRIE = 10000;

    private Logger log = LoggerFactory.getLogger(getClass());


    @Override
    protected Serializable doCreate(Session session) {
        log.info("create");
        Serializable serializable = this.generateSessionId(session);
        assignSessionId(session, serializable);
        //设置超时时间
        session.setTimeout(EXPRIE*1000);
        redisTemplate.opsForValue().set(getByteKey(serializable),SerializationUtils.serialize(session),EXPRIE);

        return serializable;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {

        Session session = null;
        Serializable s = (Serializable) redisTemplate.opsForValue().get(getByteKey(serializable));
        if (s != null) {
            session = (Session) s;
           // jedis.expire((PREFIX+serializable).getBytes(),EXPRIE);
        }
        if(session==null){
            return null;
        }
        return session;
    }

    private byte[] getByteKey(Object k){
        if(k instanceof String){
            String key = PREFIX+k;
            return key.getBytes();
        }else {
            return SerializationUtils.serialize(k);
        }
    }
    @Override
    protected void doUpdate(Session session) {
        if(session==null){
            return ;
        }
        redisTemplate.opsForValue().set(getByteKey(session.getId()),SerializationUtils.serialize(session),EXPRIE);

    }


    @Override
    protected void doDelete(Session session) {
        redisTemplate.delete(getByteKey(session.getId()));
    }
}