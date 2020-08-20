package com.runner.entity.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Reply {
    private Integer replyId;

    private Integer replyTalkId;

    private Date replyCtime;

    private Integer replyUserId;

    private Integer replyCommentId;

    private Integer replyArticleId;

    private String replyContent;
}