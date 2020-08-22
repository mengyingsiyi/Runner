package com.runner.shop.service.impl;

import com.runner.commons.dto.ShopDto.GoodsOrderDto;
import com.runner.commons.vo.R;
import com.runner.shop.dao.GoodsDao;
import com.runner.shop.dao.GoodsOrderDao;
import com.runner.shop.service.GoodsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/22  19:28
 */
@Service
public class GoodsOrderServiceImpl implements GoodsOrderService {
    @Autowired
    private GoodsDao dao;
    @Autowired
    private GoodsOrderDao orderDao;






    @Override
    public R createOrder(GoodsOrderDto dto) {
             //    查出库存
         // 生成订单
        // 付款 {注明 支付宝 还是微信 }
        //  保存订单
        // 更改库存
        //  保存日志


        // 订单超时



        return null;
    }






}
