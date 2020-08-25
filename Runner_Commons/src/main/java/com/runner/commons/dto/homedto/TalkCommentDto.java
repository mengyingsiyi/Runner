package com.runner.commons.dto.homedto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/24  20:39
 */
@Data
public class TalkCommentDto {
    private String commentUserName;
    private String commentUserHead;
    private Date commentCtime;
    private int fcount;
    private String commentDesc;
    private List<TalkReplyDto> replyDtos;
}
