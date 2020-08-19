package com.runner.cache.util;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: Redis工具类 分布式锁等
 * @author: ZackJun
 * @date: 2020/8/19  11:42
 */
public class RedissonUtil {
    private static RedissonUtil redissonUtil;

    public static RedissonUtil getInstance(String host,int port){
        Lock lock = null;
        try {
            lock = new ReentrantLock();
            lock.lock();
            if (redissonUtil ==null){
                redissonUtil = new RedissonUtil(host, port);
            }
        }finally {
            lock.unlock();
        }
        return redissonUtil;
    }
    //连接对象
    private RedissonClient client;
    private RedissonUtil(String host,int port){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://"+host+":"+port);
        client = Redisson.create(config);
    }

    /**
     * 对key加锁
     * @param key
     */
    public void lock(String key){
        client.getLock(key).lock();
    }

    /**
     * 对key加锁制定时间
     * @param key 加锁的key
     * @param timeseconds 加锁时间
     */
    public void lock(String key,long timeseconds){
        client.getLock(key).lock(timeseconds, TimeUnit.SECONDS);
    }

    /**
     * 释放对key的锁
     * @param key
     */
    public void unlock(String key){
        client.getLock(key).unlock();
    }

    /**
     * 检查key是否被加锁
     * @param key
     * @return
     */
    public boolean checkLock(String key){
        return client.getLock(key).isLocked();
    }
}
