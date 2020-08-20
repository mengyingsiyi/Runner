package com.runner.commons.dto;

import lombok.Data;

/**
 * @paragram: runner
 * @author: wangyang
 * @create: 2020/8/20 17:37
 * @Description:  验证码登录的用户类
 */
@Data
public class UserCodeLoginDto {
    private String uTel;
    private String code;
}
