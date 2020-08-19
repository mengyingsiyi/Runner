package com.runner.commons.dto;

import lombok.Data;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  16:38
 */
@Data
public class GoodsOrderDto {
    private Integer id;
    private Integer uid;
    private Integer skuid;
    private Integer num;
    private Integer aid;//收货地址
    private int dmoney;//优惠价格
}
