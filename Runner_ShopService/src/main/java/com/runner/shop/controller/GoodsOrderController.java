package com.runner.shop.controller;

import com.runner.commons.dto.ShopDto.GoodsOrderDto;
import com.runner.commons.vo.R;
import com.runner.shop.service.GoodsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/24  21:45
 */
@RestController
@RequestMapping("/server/order/")
public class GoodsOrderController {
    @Autowired
    private GoodsOrderService service;
    public R create(@RequestBody GoodsOrderDto dto ,@RequestParam String token){
            return service.createOrder(dto, token);
    }
}
