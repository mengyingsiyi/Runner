package com.runner.pay.pay.wxpay;


import com.runner.commons.dto.PayDto;
import com.runner.pay.config.PayConfig;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-13 10:12
 */
public class WxPayUtil {

    private static TreeMap<String,String> initParam(){
        TreeMap<String,String> map=new TreeMap<>();
        map.put("appid", PayConfig.APP_ID);
        map.put("mch_id", PayConfig.MCH_ID);
        map.put("nonce_str", UUID.randomUUID().toString().replaceAll("-",""));
        return map;
    }

    private static String createSign(SortedMap<String, String> packageParams) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) &&
                    !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + PayConfig.API_KEY);
        return MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
    }
    /**
     * 生成预支付连接*/
    public static String wxpay_create(PayDto dto){
        TreeMap<String,String> map=initParam();
        map.put("body",dto.getOrderdes());
        map.put("out_trade_no",dto.getOid());
        map.put("total_fee",dto.getPrice()+"");
//        map.put("total_fee",1);
        map.put("spbill_create_ip","117.159.15.221");
        map.put("notify_url",PayConfig.NOTIFY_URL);
        map.put("trade_type","NATIVE");
        map.put("sign",createSign(map));
        String requestXml=XmlUtil.createXML(map);
        System.err.println("------->"+requestXml);
        String responseXml=HttpUtil.postXml(PayConfig.WXPAY_UNIFIE,requestXml);
        System.err.println("------->"+responseXml);
        if(!StringUtils.isEmpty(responseXml)){
            Map<String,String> res=XmlUtil.parseXml(responseXml);
            return res.get("code_url");
        }
        return null;
    }
    /**
     * 查询支付状态*/
    public static String wxpay_query(String oid){
        TreeMap<String,String> map=initParam();
        map.put("out_trade_no",oid);
        map.put("sign",createSign(map));
        String requestXml=XmlUtil.createXML(map);
        String responseXml=HttpUtil.postXml(PayConfig.WXPAY_QUERY,requestXml);
        if(!StringUtils.isEmpty(responseXml)){
            Map<String,String> res=XmlUtil.parseXml(responseXml);
            //SUCCESS—支付成功
            //REFUND—转入退款
            //NOTPAY—未支付
            //CLOSED—已关闭
            //REVOKED—已撤销（付款码支付）
            //USERPAYING--用户支付中（付款码支付）
            //PAYERROR--支付失败(其他原因，如银行返回失败)
            return res.get("trade_state");
        }
        return null;
    }
    /**
     * 关闭支付*/
    public static String wxpay_close(String oid){
        TreeMap<String,String> map=initParam();
        map.put("out_trade_no",oid);
        map.put("sign",createSign(map));
        String requestXml=XmlUtil.createXML(map);
        String responseXml=HttpUtil.postXml(PayConfig.WXPAY_CLOSE,requestXml);
        if(!StringUtils.isEmpty(responseXml)){
            Map<String,String> res=XmlUtil.parseXml(responseXml);
            //SUCCESS—支付成功
            //REFUND—转入退款
            //NOTPAY—未支付
            //CLOSED—已关闭
            //REVOKED—已撤销（付款码支付）
            //USERPAYING--用户支付中（付款码支付）
            //PAYERROR--支付失败(其他原因，如银行返回失败)
            return res.get("result_code");
        }
        return null;
    }
}