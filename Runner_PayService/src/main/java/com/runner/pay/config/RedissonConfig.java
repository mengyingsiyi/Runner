package com.runner.pay.config;


import com.runner.pay.util.RedissonUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-12 10:23
 */
@Configuration
public class RedissonConfig {
    private String host="39.99.208.213";
    private int port=6379;
    private String pass="123456";
    @Bean
    public RedissonUtil createRU(){
        return RedissonUtil.getInstance(host,port,pass);
    }
}
