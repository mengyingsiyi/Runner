package com.runner.shop.service;

import com.runner.commons.dto.ShopDto.GoodsOrderDto;
import com.runner.commons.vo.R;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/22  19:26
 */

public interface GoodsOrderService {
        // 直接下单
     R    createOrder(GoodsOrderDto dto);


}
