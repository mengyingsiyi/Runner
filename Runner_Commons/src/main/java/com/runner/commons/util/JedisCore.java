package com.runner.commons.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisCore {
    private Jedis jedis;

    /**
     * jedis的工具类
     * 操作的redis
     *
     * @param host
     * @param port
     * @param pass
     */
    public JedisCore(String host, int port, String pass) {
        JedisPool pool = new JedisPool(host, port);
        jedis = pool.getResource();
        jedis.auth(pass);
    }
    //新增
    public void set(String key,String val,int seconds){
        jedis.setex(key,seconds,val);
    }
    //删除
    public void del(String key){
        jedis.del(key);
    }
    //查询
    public String get(String key){
        return jedis.get(key);
    }
    //系统
    public boolean check(String key){
        return jedis.exists(key);
    }
    //查询key的剩余时间
    public long ttl(String key){
        return jedis.ttl(key);
    }

    /**
     * 查询所有key开头的键
     * @param key
     * @return
     */
    public int keys(String key){
        return jedis.keys(key+"*").size();
    }
    //给key设置过期时间
    public void expire(String key,int seconds){
        jedis.expire(key,seconds);
    }


}
