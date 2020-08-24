package com.runner.shop.config;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/24  20:20
 */
@Configuration
public class RabbitMq {

    private String qname="open:order:ordertimeout"; // 创建延迟队列   有期限  无消费者

    private String qname2="open:order:orderxiaofei"; // 订单超时的队列去消费  有消费者

    private String  JiaoHuan="open:order:jiaohua11";// 交换机

    private String luyoupipei="open:rout:timeoutorder"; //进行 路由匹配 因为不仅有订单 还有其他的

            // 创建延迟  ，并设置有效期
      @Bean
    public Queue  getSiXin(){
          Map<String ,Object> map = new HashMap<>();
           map.put("x-message-ttl",1800000);
          map.put("x-dead-letter-exchange",JiaoHuan); //设置交换机
          map.put("x-dead-letter-routingkey",luyoupipei);// 设置路由 匹配

          return QueueBuilder.durable(qname).withArguments(map).build();
      }

        //  创建订单超时的死信的队列
      @Bean
    public Queue  getQ(){
          return new Queue(qname2);
      }
    // 创建  交换机
    @Bean
  public DirectExchange directExchange(){
            return new DirectExchange(JiaoHuan) ;
  }


    // 进行 路由 绑定
    @Bean
        public Binding   ing(DirectExchange directExchange){

            return BindingBuilder.bind(getQ()).to(directExchange).with(luyoupipei);
        }

}
