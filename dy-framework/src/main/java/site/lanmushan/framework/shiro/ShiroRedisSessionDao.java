package site.lanmushan.framework.shiro;


import site.lanmushan.framework.configure.RedisClient;
import site.lanmushan.framework.constant.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.SerializationUtils;

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
            return GlobalConstant.SESSION_ID_PREFIX + obj.toString();
        } else {
            return new String(SerializationUtils.serialize(obj));
        }
    }
}