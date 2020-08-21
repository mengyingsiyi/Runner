package com.runner.commons.dto.ShopDto;

import lombok.Data;
import java.util.List;
import com.runner.entity.pojo.*;
/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/21  20:07
 */
@Data
public class GoodsDto {
    private String goodsName;
    private Double goodsPrice;
    private Double goodsRealPrice;
    private String goodsDesc;
    private Integer goodsNum;//销量
    private List<GoodsImgUrl> url; // 图片路径
    private List<GoodsCouponDto> coupons; // 优惠表






}
