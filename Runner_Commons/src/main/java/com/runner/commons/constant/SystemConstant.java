package com.runner.commons.constant;

/**
 * @program: eBooks
 * @description
 * @author:
 * @create: 2020-08-03 20:09
 **/
public class SystemConstant {
    public static final int OSSTYPE_IMG=1;
    public static final String OSS_BUCKET = "lx-open";//文件上传
    public static final String USER_TOKEN = "USERTOKEN";//存储用户信息
    public static final String TOKEN_HEADER = "token";
    public static final String USER_PHONE="userphone";//存储用户令牌
    public static final String USER_CODE="usercode";//redis用来存储用户的验证码
    public static final String USER_MAIL_CODE="usermailcode";//redis用来存储用户邮箱的验证码
    public static final Integer CODE_TIME=60;//手机验证码过期时间
    public static final Integer MAIL_CODE_TIME=60*5;//邮箱验证码过期时间
    public static final Integer TOKEN_TIME=60*30*10;//令牌过期时间
}