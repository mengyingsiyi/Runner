package com.runner.entity.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/21  19:37
 */
@Data
public class Goods {
    private Integer id;
    private String goodsName;
    private Double goodsPrice;
    private Date goodsTime;
    private Integer goodsNum;
    private Double goodsRealPrice;
}
