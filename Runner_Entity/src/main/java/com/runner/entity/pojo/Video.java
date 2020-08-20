package com.runner.entity.pojo;

import lombok.Data;

@Data
public class Video {
    private Integer vid;

    private Integer videoTalkId;

    private String videoUrl;

    private String videoOssname;

    private String videoDesc;
}