package com.lanmushan.framework.shiro;


import com.lanmushan.framework.configure.ApplicationUtil;
import com.lanmushan.framework.configure.JedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;

import java.io.Serializable;

/**
 * 基于redis分布式session实现
 * @author Administrator
 */
@Configuration
@Order(10)
public class ShiroRedisSessionDao extends CachingSessionDAO{

    private static final String PREFIX = "SESSION_ID";

    private static final int EXPRIE = 10000;

    private Logger log = LoggerFactory.getLogger(getClass());
    JedisUtil jedisUtil;
    private void init(){
        if(jedisUtil==null)
        {
            jedisUtil= ApplicationUtil.getBean(JedisUtil.class);
        }
    }

    @Override
    protected Serializable doCreate(Session session) {
        log.info("create");
        this.init();
        Serializable serializable = this.generateSessionId(session);
        assignSessionId(session, serializable);
        Jedis jedis = jedisUtil.getJedis();
        //设置超时时间
        session.setTimeout(EXPRIE*1000);
        jedis.setex(getByteKey(serializable),EXPRIE, SerializationUtils.serialize(session) );
        jedisUtil.closeJedis(jedis);
        return serializable;
    }


    @Override
    protected Session doReadSession(Serializable serializable) {
        this.init();
        Jedis jedis = jedisUtil.getJedis();
        Session session = null;
        byte[] s = jedis.get(getByteKey(serializable));
        if (s != null) {
            session = (Session) SerializationUtils.deserialize(s);
            jedis.expire((PREFIX+serializable).getBytes(),EXPRIE);
        }
        jedisUtil.closeJedis(jedis);
        //判断是否有会话  没有返回NULL
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
        this.init();
        if(session==null){
            return ;
        }
        Jedis jedis = jedisUtil.getJedis();
        session.setTimeout(EXPRIE*1000);

        jedis.setex(getByteKey(session.getId()),EXPRIE, SerializationUtils.serialize(session) );
        jedisUtil.closeJedis(jedis);
    }


    @Override
    protected void doDelete(Session session) {
        this.init();
        Jedis jedis = jedisUtil.getJedis();
        jedis.del(getByteKey(session.getId()));
        jedisUtil.closeJedis(jedis);

    }
}