package com.runner.commons.dto.ShopDto;

//import com.sun.scenario.effect.impl.prism.PrImage;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/22  19:14
 */
@Data
@NoArgsConstructor
public class GoodsOrderDto {
    private Integer orderId;
    private Integer shopId;
    private  Integer num;
    private String add;
    private String liuYan;
    private Integer youHui;

    public GoodsOrderDto(Integer shopId, Integer num, String add, String liuYan, Integer youHui) {
        this.shopId = shopId;
        this.num = num;
        this.add = add;
        this.liuYan = liuYan;
        this.youHui = youHui;
    }
}
