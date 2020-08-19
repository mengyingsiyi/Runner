package com.runner.commons.dto;

import lombok.Data;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  16:40
 */
@Data
public class AliPayDto {
    private String out_trade_no;
    private double total_amount;
    private String subject;
}
