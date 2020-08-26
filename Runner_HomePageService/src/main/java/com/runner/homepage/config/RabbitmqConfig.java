package com.runner.homepage.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/7/24  16:20
 */
@Configuration
public class RabbitmqConfig {
    //创建队列
    @Bean
    public Queue createQ(){
        return new Queue("sendtalk");
    }
}
