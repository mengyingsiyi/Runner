package com.runner.commons.jwt;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;

/**
 * @program: OpenMain
 * @description
 * @author:
 * @create: 2020-07-31 17:44
 **/
public class JwtUtil {
    /*
    * 以Jwt算法生成
    * */
    public static String creatJwt(String msg){
        //实例化 建造器对象
        JwtBuilder builder = Jwts.builder();
        //设置内容信息
        //头部
        builder.setId(UUID.randomUUID().toString().replaceAll("-",""));
        //中部
        builder.setIssuedAt(new Date());
        //签名
        builder.setSubject(msg);
        //加密格式
        builder.signWith(SignatureAlgorithm.HS256, createKey());
        return builder.compact();
    }
    /*
    * 解析Jwt生生的密文
    * */
    public static String parseJwt(String msg){
        return  Jwts.parser().setSigningKey(createKey()).parseClaimsJws(msg).getBody().getSubject();
    }
    //生产密钥
    private static SecretKey createKey(){
        String key="javaopen2001";
       /* SecretKey secretKey=new SecretKeySpec(key.getBytes(),"AES");*/
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
        return secretKey;
    }
}
