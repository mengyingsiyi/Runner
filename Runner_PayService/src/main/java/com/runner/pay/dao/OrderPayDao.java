package com.runner.pay.dao;


import com.runner.pay.entity.OrderPay;
import org.springframework.stereotype.Repository;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-13 15:27
 */
@Repository
public interface OrderPayDao {
    int insert(OrderPay pay);
    OrderPay selectByOid(long oid);
}