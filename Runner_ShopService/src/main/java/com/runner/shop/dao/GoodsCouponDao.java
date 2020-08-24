package com.runner.shop.dao;

import com.runner.commons.dto.ShopDto.GoodsCouponDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/21  21:48
 */
@Repository
public interface GoodsCouponDao {
    List<GoodsCouponDto> selectCouponById(Integer id);
}
