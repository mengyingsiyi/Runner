package com.runner.commons.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/20  21:19
 */
@Data
public class SignInDto {
    private Integer count;   //  总签到天数
    private Integer continueSign; // 规定的时间
//    private String nickname;
    private Date signTime;


}
