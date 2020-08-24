package com.runner.commons.util;

/**
 * @program: OpenMain
 * @description:判断字符串是否为空，不是空的返回true
 * @author:
 * @create: 2020-07-28 21:16
 **/
public class StringUtil {
    public static boolean checkStr(String... str){
        boolean flag = true;
        for (String s : str) {
            if(s == null || s.length() == 0){
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 处理从redis中取的格式违规的json  去除前后引号  去除转义斜杠
     * @param userStr
     * @return
     */
    public static String jsonHandle(String userStr) {
        userStr=userStr.substring(1,userStr.length()-1);
        userStr=userStr.replace("\\","");
        return userStr;
    }

    /*public static void main(String[] args) {
         System.out.println(checkStr("123"));
    }*/
}
