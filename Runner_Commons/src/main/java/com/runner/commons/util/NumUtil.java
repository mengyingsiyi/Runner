package com.runner.commons.util;

import java.util.Random;

/**
 * @program: OpenMain
 * @description:生成随机验证码
 * @author:
 * @create: 2020-07-28 20:48
 **/
public class NumUtil {
    public static int creatCode(int len){
        Random random=new Random();
        int max= (int) (Math.pow(10,len)-Math.pow(10,len-1));
        return (int) (random.nextInt(max)+Math.pow(10,len-1));
    }
}
