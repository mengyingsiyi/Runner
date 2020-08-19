package com.runner.cache.service;

import com.runner.cache.exception.CacheException;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  11:44
 */
public interface CacheService {
    /**
     * 根据key获取缓存的String类型数据
     */
    String get(String key);

    /**
     * 设置String类型的缓存
     */
    void set(String key, String value);

    /**
     * 设置一个有过期时间的String类型的缓存,单位秒
     */
    boolean set(String key, long expireTimeSeconds, String value)throws CacheException;

    /**
     * 根据key获取缓存的Object类型数据
     */
    Object getObject(String key);

    /**
     * 设置Object类型的缓存
     */
    void setObject(String key, Object value);

    /**
     * 设置一个有过期时间的Object类型的缓存,单位秒
     */
    void setObject(String key, Object value, long timeout);

    /**
     * 根据key删除缓存的数据
     */
    boolean del(String key);


    /**
     * 判断是否存在一个key
     * */
    boolean contains(String key);

    /**
     * 设置key过期时间
     * */
    void expire(String key, long timeout);

    boolean lock(String key);

    boolean lock(String key,long timeseconds);

    boolean unlock(String key);
}
