package com.runner.pay.service.impl;

import com.alibaba.fastjson.JSON;

import com.runner.commons.dto.AliPayDto;
import com.runner.commons.dto.PayDto;
import com.runner.commons.vo.R;
import com.runner.pay.dao.OrderPayDao;
import com.runner.pay.entity.OrderPay;
import com.runner.pay.pay.alipay.AliPayUtil;
import com.runner.pay.pay.wxpay.WxPayUtil;
import com.runner.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Base64;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-13 15:32
 */
@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private OrderPayDao dao;

    @Override
    public R<String> createPay(PayDto dto) {
        if(dto.getPaytype()<3) {
            AliPayDto aliPayDto=new AliPayDto();
            String qrcodeurl=null;
            if (dto.getPaytype() == 1) {
                //支付宝
                aliPayDto.setOut_trade_no(dto.getOid());
                aliPayDto.setSubject(dto.getOrderdes());
                aliPayDto.setTotal_amount(dto.getPrice()/100.0);
                qrcodeurl= AliPayUtil.createPayUrl(JSON.toJSONString(aliPayDto));
            } else if (dto.getPaytype() == 2) {
                //微信
                qrcodeurl= WxPayUtil.wxpay_create(dto);
            }
            if(!StringUtils.isEmpty(qrcodeurl)){
                //拥有支付链接
                String url="http://localhost:8085/api/qrcode/payqrcode/"+Base64.getUrlEncoder().encodeToString(qrcodeurl.getBytes());
                OrderPay pay=new OrderPay();
                pay.setBody(dto.getOrderdes());
                pay.setOid(Long.parseLong(dto.getOid()));
                pay.setPayurl(url);
                pay.setPaymoney(dto.getPrice());
                pay.setType(dto.getPaytype());
                dao.insert(pay);
                return R.ok(url);
            }
            return R.fail("亲，第三方支付服务不可用！");
        }else {
            return R.fail("亲，你选择的支付方式目前还不支持！");
        }
    }

    @Override
    public R<String> queryPay(String oid) {
        OrderPay pay=dao.selectByOid(Long.parseLong(oid));
        if(pay!=null){
           if(pay.getFlag()==1){
               //未支付
               String r,msg=null;
               if(pay.getType()==1){
                   //支付宝
                   r=AliPayUtil.queryPay(oid);
               }else {
                   //微信支付
                   r= WxPayUtil.wxpay_query(oid);
               }
               System.err.println(r);
               if(!StringUtils.isEmpty(r)) {
                   switch (r) {
                       case "WAIT_BUYER_PAY":
                       case "NOTPAY":
                           msg = "未付款";
                           break;
                       case "TRADE_CLOSED":
                       case "CLOSED":
                       case "REVOKED":
                           msg = "交易超时关闭";
                           break;
                       case "TRADE_SUCCESS":
                       case "SUCCESS":
                           msg = "支付成功";
                           break;
                       case "TRADE_FINISHED":
                           msg = "交易结束";
                           break;
                       default:msg="异常状态";break;
                   }
               }
               return R.ok(msg);
           }
           return R.fail("");
        }else {
            return R.fail("亲，请检查订单号！");
        }
    }

    @Override
    public R<String> closePay(String oid) {
        OrderPay pay=dao.selectByOid(Long.parseLong(oid));
        if(pay!=null){
            if(pay.getFlag()==1){
                //关闭订单
                String r;
                if(pay.getType()==1){
                    r=AliPayUtil.closePay(oid);
                }else {
                    r=WxPayUtil.wxpay_close(oid);
                }
                if(StringUtils.isEmpty(r)){
                    return R.fail("关闭订单失败！");
                }else {
                    return R.ok();
                }
            }else {
                return R.fail("亲，无法关闭订单，请检查订单状态");
            }
        }else {
            return R.fail("亲，请检查订单号！");
        }
    }
}