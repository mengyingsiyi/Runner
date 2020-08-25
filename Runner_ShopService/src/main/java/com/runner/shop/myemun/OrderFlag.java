package com.runner.shop.myemun;

/**
 * @Description:  订单的各种不同的状态
 * @author: ZackJun
 * @date: 2020/8/24  20:58
 */
public enum OrderFlag {
     待支付(1) , 待发货(2) , 超时订单(3), 订单完成(4);
    private int val;

    public int getVal() {
        return val;
    }
    private OrderFlag(int v){
        val=v;
    }
}
