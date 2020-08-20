package com.runner.entity.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  11:20
 */
@Data
public class OssOp {
    private Integer ossid;
    private Integer type;
    private Date ctime;
    private String bname;
    private String objname;
}
