package com.runner.entity.pojo;

import lombok.Data;

@Data
public class Topic {
    private Integer topicId;

    private String topicName;

    private Integer topicUserId;

    private Integer topicTalkId;

    private Double topicPeoples;

    private Double topicWatch;

    private String topicContent;
}