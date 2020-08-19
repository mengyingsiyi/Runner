package com.runner.pay.entity;

import lombok.Data;

import java.util.Date;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-13 15:25
 */
@Data
public class OrderPay {
    private long id;
    private long oid;
    private String body;
    private Integer paymoney;
    private Integer type;
    private Integer flag;
    private String payurl;
    private Date ctime;
}