package com.runner.cache.service.impl;

import com.runner.cache.exception.CacheException;
import com.runner.cache.service.CacheService;
import com.runner.cache.util.RedissonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  11:45
 */
@Service
@RefreshScope//  动态拿数据 从配置中心

public class CacheServiceImpl implements CacheService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //从注册中心取出redis地址，端口，密码
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
//    @Value("${spring.redis.password}")
//    private String password;

    private RedissonUtil redissonUtil;
    @PostConstruct
    public void init(){
//        log.error(host);
        redissonUtil = RedissonUtil.getInstance(host,port);
    }

    /**
     *
     * @param key key
     * @param expireTimeSeconds 如果为-1则永久有效
     * @param value  value
     * @return 新增是否成功
     * @throws CacheException 存储异常
     */
    @Override
    public boolean set(String key, long expireTimeSeconds, String value) throws CacheException {
        try {
            if (expireTimeSeconds < -1){
                expireTimeSeconds = -1;
            }
            stringRedisTemplate.opsForValue().set(key,value,expireTimeSeconds, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
//            log.error("存储异常");
            throw new CacheException("存储异常"+e.getMessage());
        }
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key,value);
    }


    @Override
    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public void setObject(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.SECONDS);
    }

    @Override
    public boolean del(String key) {
        redisTemplate.delete(key);
        stringRedisTemplate.delete(key);
        return true;
    }

    @Override
    public boolean contains(String key) {
        return redisTemplate.hasKey(key) || stringRedisTemplate.hasKey(key);
    }

    @Override
    public void expire(String key, long timeout) {
        redisTemplate.expire(key,timeout, TimeUnit.SECONDS);
        stringRedisTemplate.expire(key,timeout, TimeUnit.SECONDS);
    }

    @Override
    public boolean lock(String key) {
        redissonUtil.lock(key);
        return true;
    }

    @Override
    public boolean lock(String key, long timeseconds) {
        redissonUtil.lock(key,timeseconds);
        return true;
    }

    @Override
    public boolean unlock(String key) {
        redissonUtil.unlock(key);
        return true;
    }
}
