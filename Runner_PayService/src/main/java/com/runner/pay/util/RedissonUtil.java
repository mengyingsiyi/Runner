package com.runner.pay.util;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: FmOpen
 * @description: 单例模式  懒汉
 * @author: Feri(邢朋辉)
 * @create: 2020-08-06 10:35
 */
public class RedissonUtil {
    private static RedissonUtil redissonUtil;
    public static RedissonUtil getInstance(String host, int port, String pass){
        Lock lock=null;
        try {
            lock=new ReentrantLock();
            lock.lock();
            if(redissonUtil==null) {
                redissonUtil = new RedissonUtil(host, port, pass);
            }
        }finally {
            lock.unlock();
        }
        return redissonUtil;
    }

    //加锁 释放锁
    private RedissonClient client;
    private RedissonUtil(String host, int port, String pass){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://"+host+":"+port).setPassword(pass);
        client= Redisson.create(config);
    }
    public void saveStr(String key,String value){
        client.getBucket(key).set(value);
//        client.getMap(key).put();
//        client.getScoredSortedSet(key).add();
//        client.getList(key);
//        client.getSet().add();
//        client.getGeo(key).add();
    }
    public void lock(String key){
        client.getLock(key).lock();
    }
    public void lock(String key,long timeseconds){
        client.getLock(key).lock(timeseconds, TimeUnit.SECONDS);
    }
    public void unlock(String key) {
        client.getLock(key).unlock();
    }
    public boolean checkLock(String key){ return client.getLock(key).isLocked(); }
}
