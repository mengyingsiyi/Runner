package com.runner.pay.controller;


import com.runner.commons.dto.PayDto;
import com.runner.commons.vo.R;
import com.runner.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-13 15:55
 */
@RestController
@RequestMapping("/api/pay/")
public class PayController {
    @Autowired
    private PayService service;

    @PostMapping("sendpay")
    public R<String> createPay(@RequestBody PayDto dto){
        return service.createPay(dto);
    }
    @GetMapping("querypay/{oid}")
    public R queryPay(@PathVariable String oid){
        return service.queryPay(oid);
    }
    @PostMapping("closepay/{oid}")
    public R refundPay(@PathVariable String oid){
        return service.closePay(oid);
    }
}
