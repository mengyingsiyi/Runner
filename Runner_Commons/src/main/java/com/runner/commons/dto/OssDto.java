package com.runner.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  13:49
 */
@Data
@NoArgsConstructor
public class OssDto implements Serializable {
    private Integer oid;
    private Integer type;
    private String bname;
    private String objname;

    public OssDto(Integer type, String bname, String objname) {
        this.type = type;
        this.bname = bname;
        this.objname = objname;
    }
}
