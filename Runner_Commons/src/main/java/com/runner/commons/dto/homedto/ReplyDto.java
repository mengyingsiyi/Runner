package com.runner.commons.dto.homedto;

import lombok.Data;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/21  19:12
 */
@Data
public class ReplyDto {
    private Integer talkId;
    private Integer touid;
    private Integer commentId;
    private String replyContent;
}
