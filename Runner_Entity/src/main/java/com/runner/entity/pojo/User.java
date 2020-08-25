package com.runner.entity.pojo;

import lombok.Data;

/**
 * @paragram: Keep
 * @author: wangyang
 * @create: 2020/8/19 14:55
 * @Description:  User实体类
 */
@Data
public class User {
    private Integer uId;
    private String uNickname;//狗鹏
    private String uTel;
    private String uPassword;
    private String uHead;
    private String uSex;
    private Double uHeight;
    private Double uWeight;
    private Integer uBirth;
    private String uAddr;
    private String uAutograph;
    private Integer uStatus;
    private Integer uLevel;

}
