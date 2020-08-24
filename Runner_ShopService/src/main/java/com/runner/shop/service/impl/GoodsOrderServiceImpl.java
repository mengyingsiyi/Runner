package com.runner.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.dto.ShopDto.GoodsDto;
import com.runner.commons.dto.ShopDto.GoodsOrderDto;
import com.runner.commons.dto.ShopDto.MQDto;
import com.runner.commons.util.IdGenerator;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.Order;
import com.runner.entity.pojo.User;
import com.runner.shop.dao.GoodsDao;
import com.runner.shop.dao.GoodsOrderDao;
import com.runner.shop.service.CacheServer;
import com.runner.shop.service.GoodsOrderService;
import com.runner.shop.service.PayServer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Description:   订单服务
 * @author: ZackJun
 * @date: 2020/8/22  19:28
 */
@Service
public class GoodsOrderServiceImpl implements GoodsOrderService {
    @Autowired
    private GoodsDao dao;
    @Autowired
    private CacheServer cacheServer;
    @Autowired
    private GoodsOrderDao orderDao;
    @Autowired
    private IdGenerator generator;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private PayServer payserver;

    @Override
    public R createOrder(GoodsOrderDto dto, String token) {
        if (!StringUtil.checkStr(token)) {
            User user = getUser(token);
            GoodsDto goodsDto = dao.selectById(user.getUId());
            //    查出库存
            Integer psc = dao.selectInventory(dto.getShopId());
            if (psc > dto.getNum()) {
                // 生成订单   查询 优惠表（没写。。。。）
                Order order = new Order();
                order.setOrderId(generator.nextId()); //  雪花算法 生成id
                order.setOrderAdd(dto.getAdd());
                order.setShopId(dto.getShopId());
                order.setGuestBook(dto.getLiuYan());
                order.setCoupon(dto.getYouHui());
                order.setRealPrice(dto.getNum() * goodsDto.getGoodsRealPrice() - order.getCoupon());
                //  保存订单
                if (orderDao.save(order) > 0) {
                    // 更改库存
                    if (dao.updateInv(dto.getNum(), dto.getShopId()) > 0) {
                        //  保存日志
                        return R.ok("订单成功");
                    }
                    // 查询 支付结果
                    R r = payserver.queryPay(String.valueOf(order.getOrderId()));


                    if (r.getCode().equals(10000)) {

                    } else {
                        // 订单超时
                        MQDto<Long> msgDto = new MQDto<>(generator.nextId(), 1, order.getOrderId());
                        template.convertAndSend(null, "open:order:timeoutorder", msgDto);

                    }


                } else {
                    return R.fail("保存订单失败");
                }


            } else {
                return R.fail("当前库存不足");
            }

        } else {
            return R.fail("请先登录");
        }

        return R.fail("出现了位置错误");
    }


    //检查token
    public User getUser(String token) {
        if (StringUtil.checkStr(token)) {
            if (cacheServer.check(token)) {
                String s = cacheServer.get(token);
                if (StringUtil.checkStr(s)) {
                    return JSON.parseObject(s, User.class);
                }
            }
        }
        return null;
    }


}
