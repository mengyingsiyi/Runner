package com.runner.shop.listen;

import com.runner.commons.dto.ShopDto.MQDto;
import com.runner.entity.pojo.Order;
import com.runner.shop.dao.GoodsOrderDao;
import com.runner.shop.myemun.OrderFlag;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:   监听消息队列
 * @author: ZackJun
 * @date: 2020/8/24  20:43
 */
@Component
@RabbitListener(queues = "open:order:orderxiaofei") // 通过队列名
public class TimeOutOrder {
            @Autowired
            private GoodsOrderDao dao;
             @RabbitHandler // 修饰方法    用来获取值
            @Transactional
            public void   hand(MQDto<Long> msgDto ){
                   //   查询订单信息
                 Order byId = dao.findById(msgDto.getId());
                   if (null!=byId) {
                       if (byId.getFlag().equals(OrderFlag.待支付)) {
                            //  该订单 超时订单 直接释放 资源

                       } else if (byId.getFlag().equals(OrderFlag.超时订单)) {
                            //  超时订单 直接释放资源

                       }

                   }
             }




}
