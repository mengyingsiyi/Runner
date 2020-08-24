package com.runner.commons.dto.ShopDto;

//import com.sun.scenario.effect.impl.prism.PrImage;
import lombok.Data;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/21  20:29
 */
@Data
public class GoodsAllDto {
    private String goodsImgUrl;
    private Double goodsPrice;
    private Double goodsRealPrice;
    private Integer sales; // 总销量 
    private String goodsDesc; 
    
}
