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
    @Select("")
    Integer selectInventory(Integer shopId);
}
