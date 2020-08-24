package com.runner.commons.dto.homedto;

import lombok.Data;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/21  18:48
 */
@Data
public class CommentDto {
    private String commentUserName;
    private Integer talkId;
    private String content;
}
