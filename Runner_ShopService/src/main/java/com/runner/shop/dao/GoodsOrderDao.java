package com.runner.shop.dao;

import com.runner.commons.dto.ShopDto.GoodsOrderDto;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/22  19:18
 */
@Repository
public interface GoodsOrderDao {
    int save(Order order);
    Order findById(Long id);
    int updateFlagById(@Param("id") Integer id, @Param("flag") int flag);
}
