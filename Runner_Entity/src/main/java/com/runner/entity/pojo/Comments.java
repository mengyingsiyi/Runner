package com.runner.entity.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/24  19:41
 */
@Data
public class Comments {
    private Integer commentId;
    private Integer userId;
    private String commentDesc;
    private Integer talkId;
    private Date ctime;
    private Integer articleId;
    private String username;
}
