package com.runner.commons.dto;

import lombok.Data;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  16:27
 */
@Data
public class PayDto {
    private String oid;//订单号
    private int price;//单位分
    private String orderdes;//订单的描述信息
    private int paytype;//支付方式 1 支付宝 2微信
}
