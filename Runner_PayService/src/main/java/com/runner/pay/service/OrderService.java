package com.runner.pay.service;


import com.runner.commons.dto.GoodsOrderDto;
import com.runner.commons.vo.R;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-12 09:05
 */
public interface OrderService {

    //商品详情 立即购买  只能购买一种商品
    R createOrder(GoodsOrderDto dto);

    //购物车选中  购买  可以购买多种商品



}
