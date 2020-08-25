package com.runner.commons.dto.homedto;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/21  19:12
 */
@Data
public class ReplyDto {

    private Integer replyTalkId;

    private Integer replyCommentId;

    private int replyToUserId;

    private String replyContent;
}
