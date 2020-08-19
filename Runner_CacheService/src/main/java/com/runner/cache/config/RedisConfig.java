package com.runner.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description:  redis配置
 * @author: ZackJun
 * @date: 2020/8/19  11:40
 */
@Configuration
@EnableCaching  //启用缓存
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 自定义key的生成策略
     *
     * @return
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuffer buffer = new StringBuffer();
            buffer.append(target.getClass().getName() + ":");
            buffer.append(method.getName() + ":");
            for (Object o : params) {
                buffer.append(o.toString());
            }
            return buffer.toString();
        };
    }

    //创建缓存管理器
    @Bean
    public CacheManager createCM(LettuceConnectionFactory connectionFactory) {
        //Redis写入对象
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        return new RedisCacheManager(writer, RedisCacheConfiguration.defaultCacheConfig());
    }

    //重新Redis模板对象，可以存储任意类型 ： 对象存储 序列化存储  实现序列化接口
    @Bean
    public RedisTemplate<String, Object> createRT(LettuceConnectionFactory connectionFactory) {
        //实例化对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //设置连接对象
        template.setConnectionFactory(connectionFactory);
        //设置序列化格式
        StringRedisSerializer redisSerializer = new StringRedisSerializer();
        template.setKeySerializer(redisSerializer);
        template.setHashValueSerializer(redisSerializer);
        //设置内容序列化格式
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //设置序列化
        ObjectMapper objectMapper = new ObjectMapper();
        //设置
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        //字符串，set，zset，list
        template.setValueSerializer(jsonRedisSerializer);
        //设置hash的value序列化
        template.setHashValueSerializer(jsonRedisSerializer);
        return template;
    }
}
