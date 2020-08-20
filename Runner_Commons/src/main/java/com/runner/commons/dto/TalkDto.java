package com.runner.commons.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  10:55
 */
@Data
public class TalkDto {

    private Integer talkUserId;

    private Date talkCtime;

    private Integer talkType;

    private Integer talkTopicId;

    private String talkContent;
}
