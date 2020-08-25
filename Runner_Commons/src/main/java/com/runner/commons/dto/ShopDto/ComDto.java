package com.runner.commons.dto.ShopDto;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/25  19:46
 */
@Data
public class ComDto {
    private Integer comId;
    private String comImgUrl;
    private String comVideoUrl;
    private Integer comOrderId;
    private  String  comDesc;
    private Integer  type ; // 主要为了区分 是 图片还是 视频

}
