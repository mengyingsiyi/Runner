package com.runner.pay.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-12 09:43
 */
@Configuration
public class RabbitConfig {
    private String qname1="open:order:timeoutorder";//延迟队列 有效期  无消费者

    private String qname2="open:order:dlxtimeorder";//订单超时的死信队列 有消费者

    private String exchange1="open:dlxexchange";//死信交换器

    private String routkey1="open:rout:timeoutorder";//路由匹配 超时订单
    @Bean
    public Queue createQ1(){
        Map<String,Object> params=new HashMap<>();
        //设置参数 3个参数 固定
        //设置内部消息的有效期 也就是失效的时间
        params.put("x-message-ttl",180000);
        //设置死信交换器
        params.put("x-dead-letter-exchange",exchange1);
        //设置路由匹配
        params.put("x-dead-letter-routingkey",routkey1);
        return QueueBuilder.durable(qname1).withArguments(params).build();
    }
    @Bean
    public Queue createQ2(){
        return new Queue(qname2);
    }
    //创建交换器
    @Bean
    public DirectExchange createDE(){
        return new DirectExchange(exchange1);
    }
    //绑定
    @Bean
    public Binding createBD(DirectExchange exchange){
        return BindingBuilder.bind(createQ2()).to(exchange).with(routkey1);
    }
}
