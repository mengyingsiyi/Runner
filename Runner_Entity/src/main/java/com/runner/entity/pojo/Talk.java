package com.runner.entity.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Talk {
    private Integer talkId;

    private Integer talkUserId;

    private Date talkCtime;

    private Integer talkType;

    private Integer talkTopicId;

    private String talkContent;
}