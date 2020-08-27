package com.runner.entity.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:  评论
 * @author: ZackJun
 * @date: 2020/8/25  19:41
 */
@Data
public class Comment {
     private Integer comId;
     private String comImgUrl;
     private String comVideoUrl;
     private Integer comOrderId;
     private Date ctime;
     private  String  comDesc;

}
