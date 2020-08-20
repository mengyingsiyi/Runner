package com.runner.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  13:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OssDto {
    private Integer type;
    private String bname;
    private String objname;
}
