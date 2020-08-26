package com.runner.commons.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  17:44
 */
@Data
public class VideoDto implements Serializable {
    private int talkId;
    private String videoUrl;
    private int ossId;
    private int articleId;
}
