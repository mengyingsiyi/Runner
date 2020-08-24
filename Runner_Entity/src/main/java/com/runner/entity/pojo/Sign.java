package com.runner.entity.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/23  19:50
 */
@Data
public class Sign {
    private Integer id;
    private Integer uid;
    private Date ctime; // 签到时间
    private Date uptime;
    private Integer flag;
    private Integer total;
    private Integer lianXu;
    private Integer weekSign;
}
