package com.runner.entity.pojo;

import lombok.Data;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/21  21:12
 */
@Data
public class GoodsCoupon {
    private Integer id;
    private Integer couponMax; // 满足多少钱
    private Integer couponPar;// 减去多少钱
    private Integer shopId;

}
