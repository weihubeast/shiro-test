package com.baidu.shiro.session;

import com.baidu.shiro.util.JedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: WH
 * @Date: 2019/3/4 15:25
 * @Version 1.0
 */
public class RedisSessionDao extends AbstractSessionDAO {
    @Resource
    private JedisUtil jedisUtil;
    //session前缀
    private final String SHIRO_SESSION_PREFIX = "wh-session:";

    private byte[] getkey(String key) {
        return (SHIRO_SESSION_PREFIX + key).getBytes();
    }

    //保存session
    private void saveSession(Session session) {
        if (session != null && session.getId() != null) {
            //通过id得到session 的 key
            byte[] key = getkey(session.getId().toString());
            //将value序列号为byte数组
            byte[] value = SerializationUtils.serialize(session);
            jedisUtil.set(key, value);
            //设置超时时间 600秒
            jedisUtil.expire(key, 600);
        }
    }

    //创建session
    @Override
    protected Serializable doCreate(Session session) {
        //获取session id
        Serializable sessionid = generateSessionId(session);
        //将session 和sessionid捆绑
        assignSessionId(session, sessionid);
        saveSession(session);
        return sessionid;
    }

    //获得session
    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("read session");
        if (sessionId == null) {
            return  null;
        }
        byte[] key = getkey(sessionId.toString());
        byte[] value = jedisUtil.get(key);
        //通过反序列化将byte数组反序列化为session对象
        return (Session) SerializationUtils.deserialize(value);
    }

    //更新session
    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);

    }

    //删除session
    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }
        byte[] key = getkey(session.getId().toString());
        jedisUtil.del(key);

    }

    //获得指定存活的session
    @Override
    public Collection<Session> getActiveSessions() {
        //获得所有key
        Set<byte[]> keys = jedisUtil.keys(SHIRO_SESSION_PREFIX);
        Set<Session> sessions = new HashSet<>();
        if (CollectionUtils.isEmpty(keys)) {
            return  sessions;
        }
        for (byte[] key : keys) {
            //反序列化为session对象
            Session session = (Session) SerializationUtils.deserialize(jedisUtil.get(key));
            sessions.add(session);
        }
        return sessions;
    }
}
