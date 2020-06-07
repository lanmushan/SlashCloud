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
 *
 * @author Administrator
 */
@Configuration
@Order(10)
public class ShiroRedisSessionDao extends CachingSessionDAO {

    private static final String PREFIX = "SESSION_ID";
    private static final int EXPRIE = 10000;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    private Logger logger= LoggerFactory.getLogger(ShiroRedisSessionDao.class);

    @Override
    protected void doUpdate(Session session) {

    }

    @Override
    protected void doDelete(Session session) {

    }

    @Override
    protected Serializable doCreate(Session session) {

        Serializable serializable = this.generateSessionId(session);
        assignSessionId(session, serializable);
        String key=getKey(session.getId());
        if(logger.isDebugEnabled())
        {
            logger.debug("redis session do create key:{}",key);
        }
        redisTemplate.opsForValue().set(key, SerializationUtils.serialize(session), EXPRIE);
        return serializable;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
      return (Session) redisTemplate.opsForValue().get(getKey(serializable));
    }

    private String getKey(Object obj) {
        if (obj instanceof String) {
            return PREFIX + obj.toString();
        } else {
            return new String(SerializationUtils.serialize(obj));
        }
    }
}