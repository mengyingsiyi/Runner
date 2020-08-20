package com.runner.entity.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 文章表实体类
 */
@Data
public class Article {
    private Integer articleId;//文章id

    private String articleTitle;//文章标题

    private Integer articleUserId;//用户id

    private Integer articleWords;//文章字数

    private Date articleCtime;//发表时间

    private Integer articleTypeId;//文章类型id

    private String articleContent;//文章介绍
}