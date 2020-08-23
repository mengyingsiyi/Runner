package com.runner.commons.dto.ShopDto;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/23  19:59
 */
@Data
public class SignDto {
    private Integer count; //  总时长
    private Integer continueSign; //签到总天数
    private Date signTime; // 签到时间
}
