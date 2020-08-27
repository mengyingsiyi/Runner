package com.runner.entity.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:  订单
 * @author: ZackJun
 * @date: 2020/8/22  19:09
 */
@Data
public class Order {

     private long orderId;
       private String orderAdd;
        private Integer shopId;
        private Double price;
        private Double realPrice;
        private Integer coupon; // 订单优惠价格
     private String guestBook;// 留言
     private Date ctime;
     private Integer userId;
     private Integer flag;


}
