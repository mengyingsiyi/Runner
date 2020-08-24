package com.runner.shop.dao;

import com.runner.commons.dto.ShopDto.GoodsDto;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/21  21:14
 */
@Repository
public interface GoodsDao {

    GoodsDto  selectById(Integer id);
        // 通过 商品id查询到  库存
    Integer selectInventory(Integer shopId);
        // 修改 库存
    int updateInv(Integer num ,  Integer shopId);
}
