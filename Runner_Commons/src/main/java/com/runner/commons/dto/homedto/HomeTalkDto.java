package com.runner.commons.dto.homedto;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/22  19:24
 */
@Data
public class HomeTalkDto {
    private Integer talkId;
    private String nickname;
    private String head;
    private String content;
    private Integer lookcount;
    private List<HomePicDto> picurl;

    private Integer fabulousCount;
}
