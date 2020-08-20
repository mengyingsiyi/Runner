package com.runner.entity.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Collection {
    private Integer collectionId;

    private Integer collectionUserId;

    private Integer collectionTalkId;

    private Date collectionCtime;

    private Integer collectionArticleId;
}