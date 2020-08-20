package com.runner.commons.util;

import java.util.UUID;

/**
 * @paragram: runner
 * @author: wangyang
 * @create: 2020/8/20 14:01
 * @Description:  令牌工具类
 */
public class TokenUtil {
        //生成令牌 唯一 组成格式：uuid+uid
        public static String createToken(int uid){
            return UUID.randomUUID().toString().replaceAll("-","")+"_"+uid;
        }

}
