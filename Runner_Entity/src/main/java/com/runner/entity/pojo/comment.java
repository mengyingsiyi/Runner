package com.runner.entity.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer commentId;

    private Integer commentUserId;

    private Integer commentTalkId;

    private Date commentCtime;

    private Integer commentArticleId;

    private String commentDesc;
}