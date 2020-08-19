package com.runner.pay.service;

import com.runner.commons.dto.PayDto;
import com.runner.commons.vo.R;

/**
 * @Description:  统一支付接口
 * @author: ZackJun
 * @date: 2020/8/19  16:31
 */
public interface PayService {
    //生成支付信息，返回支付二维码
    R<String> createPay(PayDto dto);
    //查询支付状态
    R<String> queryPay(String oid);
    //关闭订单
    R<String> closePay(String oid);
}
