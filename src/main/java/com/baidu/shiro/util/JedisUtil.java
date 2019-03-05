package com.baidu.shiro.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Author: WH
 * @Date: 2019/3/4 15:33
 * @Version 1.0
 */
@Component
public class JedisUtil {
    //创建JedisPool连接池获取连接
    @Autowired
    private JedisPool jedisPool;

    //获得连接
    private Jedis getResource() {
        return  jedisPool.getResource();
    }
    //设置key 和value
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = getResource();
        try {
            jedis.set(key, value);
            return  value;
        } finally {
            jedis.close();
        }
    }


    public void expire(byte[] key, int i) {

        Jedis jedis = getResource();
        try {
            //设置指定key的超时时间
            jedis.expire(key, i);
        } finally {
            jedis.close();
        }

    }
    //获取session
    public byte[] get(byte[] key) {
        Jedis jedis = getResource();
        try {
            return  jedis.get(key);
        } finally {
            jedis.close();
        }
    }
    //删除key
    public void del(byte[] key) {
        Jedis jedis = getResource();
        try {
              jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public Set<byte[]> keys(String shiro_session_prefix) {
        Jedis jedis = getResource();
        try {
           return  jedis.keys((shiro_session_prefix + "*").getBytes());
        } finally {
            jedis.close();
        }
    }
}
