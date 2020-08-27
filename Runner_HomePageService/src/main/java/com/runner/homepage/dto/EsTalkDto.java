package com.runner.homepage.dto;

import com.runner.commons.dto.homedto.HomePicDto;
import com.runner.commons.dto.homedto.TalkCommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/25  19:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "hometalk")
public class EsTalkDto implements Serializable {
    @Id
    private Integer talkId;
    private String nickname;
    private String head;
    private String content;
    private Integer lookcount;
    private List<HomePicDto> picurl;
    private Integer fabulousCount;
}
