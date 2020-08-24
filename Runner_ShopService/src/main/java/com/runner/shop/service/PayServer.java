package com.runner.shop.service;

import com.runner.commons.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/24  21:13
 */
@FeignClient(name = "orderserver")
public interface PayServer {
    @GetMapping("querypay/{oid}")
     R queryPay(@PathVariable String oid);


}
