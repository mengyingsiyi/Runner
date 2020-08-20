package com.runner.commons.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  13:49
 */
@Data
public class OssDto {
    private Integer type;
    private Date ctime;
    private String bname;
    private String objname;
}
