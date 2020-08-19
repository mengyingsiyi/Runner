package com.runner.pay.pay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.runner.pay.config.PayConfig;


/**
 * @program: FmOpen
 * @description: 封装支付宝支付
 * @author: Feri(邢朋辉)
 * @create: 2020-08-12 16:57
 */
public class AliPayUtil {
    private static AlipayClient alipayClient ;
    static {
        alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                PayConfig.ALIPAY_APPID,
                PayConfig.ALIPAY_PRIVATEKEY,
                "json","UTF-8",
                PayConfig.ALIPAY_PUBLICKEY,"RSA2");
    }

    /**
     * 生成支付链接*/
    public static String createPayUrl(String payjson){
        //创建请求对象
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        //设置请求参数 必选
        request.setBizContent(payjson);
        try {//发起请求 获取响应
            AlipayTradePrecreateResponse response=alipayClient.execute(request);
            if(response.isSuccess()){
                return response.getQrCode();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 撤消支付*/
    public static String cancelPay(String oid){
        AlipayTradeCancelRequest request=new AlipayTradeCancelRequest();
        request.setBizContent("{\"trade_no\":"+oid+"}");
        try {
            AlipayTradeCancelResponse response=alipayClient.execute(request);
            if(response.isSuccess()){
                return response.getAction();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 关闭支付*/
    public static String closePay(String oid){
        AlipayTradeCloseRequest request=new AlipayTradeCloseRequest();
        request.setBizContent("{\"trade_no\":"+oid+"}");
        try {
            AlipayTradeCloseResponse response=alipayClient.execute(request);
            if(response.isSuccess()){
                return "OK";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;

    }
    /**
     * 查询支付状态
     * 交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）
     * 、TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）*/
    public static String queryPay(String oid){
        AlipayTradeQueryRequest request=new AlipayTradeQueryRequest();
        request.setBizContent("{\"out_trade_no\":"+oid+"}");
        try {
            AlipayTradeQueryResponse response=alipayClient.execute(request);
            if(response.isSuccess()){
                return response.getTradeStatus();
            }
            System.err.println(response.getMsg());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;

    }
    /**
     * 退款*/
    public static boolean refundPay(String payjson){
        AlipayTradeRefundRequest request=new AlipayTradeRefundRequest();
        request.setBizContent(payjson);
        try {
            AlipayTradeRefundResponse response=alipayClient.execute(request);
            if(response.isSuccess()){
                return true;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return false;

    }
    /**
     * 查询退款进度*/
    public static boolean refundQueryPay(String payjson){
        AlipayTradeFastpayRefundQueryRequest  request=new AlipayTradeFastpayRefundQueryRequest ();
        request.setBizContent(payjson);
        try {
            AlipayTradeFastpayRefundQueryResponse  response=alipayClient.execute(request);
            if(response.isSuccess()){
                return true;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return false;

    }
}
