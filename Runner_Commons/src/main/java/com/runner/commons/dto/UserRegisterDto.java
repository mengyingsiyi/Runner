package com.runner.commons.dto;

import lombok.Data;

/**
 * @paragram: Runner
 * @author: wangyang
 * @create: 2020/8/19 21:43
 * @Description:
 */
@Data
public class UserRegisterDto {
    private String uNickname;
    private String uTel;
    private String uPassword;
    private String uSex;
 /*   private Integer uStatus;
    private Integer uLevel;*/
}
