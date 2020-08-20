package com.runner.entity.pojo;

import lombok.Data;

/**
 * 文章内容表
 */
@Data
public class ArticleContent {
    private Integer articleContentId;

    private Integer articleId;

    private String articleContent;
}