package com.runner.commons.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @program: HungryApi
 * @description: 日期
 * @author: Feri(邢朋辉)
 * @create: 2020-06-19 10:01
 */
public class DateUtil {
    /**
     * @param com  要进行操作的值
     * @param type 日历字段*/
    public static Date comDate(int type,int com){
        Calendar calendar=Calendar.getInstance();
        calendar.add(type,com);
        return calendar.getTime();
    }
}
