package com.runner.commons.dto.ShopDto;

import lombok.Data;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/21  21:47
 */
@Data
public class GoodsCouponDto {
    private Integer couponMax; // 满足多少钱
    private Integer couponPar;// 减去多少钱
}
