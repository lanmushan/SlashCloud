package com.lanmushan.framework.shiro;


import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.lanmushan.framework.configure.RedisClient;
import lombok.extern.slf4j.Slf4j;
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
import redis.clients.jedis.Jedis;

import java.io.Serializable;

/**
 * 基于redis分布式session实现
 *
 * @author Administrator
 */
@Configuration
@Order(10)
@Slf4j
public class ShiroRedisSessionDao extends CachingSessionDAO {

    private static final String PREFIX = "SESSION_ID|";
    private static final int EXPRIE = 10000;
    @Autowired
    RedisClient redisClient;

    @Override
    protected void doUpdate(Session session) {
        String key = getKey(session.getId());
        log.info("更新Session|" + key);
        redisClient.setObject(key, session);
    }

    @Override
    protected void doDelete(Session session) {
        String key = getKey(session.getId());
        log.info("刪除Session|" + key);
        redisClient.deleteObject(getKey(session));
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable serializable = this.generateSessionId(session);
        assignSessionId(session, serializable);
        String key = getKey(serializable);
        log.info("创建Session|" + key);
        redisClient.setObject(key, session);
        return serializable;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        String key = getKey(serializable);
        log.info("读取Session|" + key);
        Session session = (Session) redisClient.getObject(key);
        if (session == null) {
            return null;
        }
        return session;
    }

    private String getKey(Object obj) {
        if (obj instanceof String) {
            return PREFIX + obj.toString();
        } else {
            return new String(SerializationUtils.serialize(obj));
        }
    }
}