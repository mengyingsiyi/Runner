package com.runner.commons.dto.homedto;

import lombok.Data;

import java.sql.Date;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/24  20:58
 */
@Data
public class TalkReplyDto {
    private String replyUserName;
    private String replyUserHead;
    private Date replyCtime;
    private String toUserName;
    private String replyDesc;
    private int frCount;
}
