package com.runner.commons.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  10:55
 */
@Data
public class TalkDto implements Serializable {
    private Integer tid;

    private Integer talkType;

    private Integer talkTopicId;

    private String talkContent;
}
