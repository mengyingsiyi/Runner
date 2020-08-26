package com.runner.commons.dto.homedto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/23  20:20
 */
@Data
public class TalkDetailDto {
    private String username;
    private String head;
    private String content;
    private List<HomePicDto> picDtos;
    private int commentCount;
    private int fcount;
    private int collectionCount;
    private int lookcount;
    private List<TalkCommentDto> commentDtos;
}
