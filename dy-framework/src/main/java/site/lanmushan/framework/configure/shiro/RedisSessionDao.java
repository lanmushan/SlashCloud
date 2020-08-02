package site.lanmushan.framework.configure.shiro;



import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import site.lanmushan.framework.configure.RedisClient;

import java.io.Serializable;
@Component
public class RedisSessionDao extends CachingSessionDAO {

    private static final String PREFIX = "SESSION_ID";

    private static final int EXPRIE = 100000;

    @Autowired
    RedisClient redisClient;

    @Override
    protected Serializable doCreate(Session session) {

        Serializable serializable = this.generateSessionId(session);
        assignSessionId(session, serializable);
        Jedis jedis = redisClient.getResource();
        session.setTimeout(EXPRIE*1000);
        jedis.setex(getByteKey(serializable),EXPRIE, SerializationUtils.serialize((Serializable)session) );
        redisClient.close(jedis);
        return serializable;
    }


    @Override
    protected Session doReadSession(Serializable serializable) {
        Jedis jedis = redisClient.getResource();
        Session session = null;
        byte[] s = jedis.get(getByteKey(serializable));
        if (s != null) {
            session = (Session) SerializationUtils.deserialize(s);
            jedis.expire((PREFIX+serializable).getBytes(),EXPRIE);
        }
        redisClient.close(jedis);
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
            return SerializationUtils.serialize((Serializable) k);
        }
    }
    @Override
    protected void doUpdate(Session session) {
        if(session==null){
            return ;
        }
        Jedis jedis = redisClient.getResource();
        session.setTimeout(EXPRIE*1000);
        jedis.setex(getByteKey(session.getId()),EXPRIE, SerializationUtils.serialize((Serializable)session) );
        redisClient.close(jedis);
    }


    @Override
    protected void doDelete(Session session) {
        Jedis jedis = redisClient.getResource();
        jedis.del(getByteKey(session.getId()));
        redisClient.close(jedis);

    }

}
