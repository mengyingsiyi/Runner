package com.runner.shop.dao;

import com.runner.commons.dto.ShopDto.GoodsAllDto;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/21  20:35
 */
@Repository
public interface GoodsAllDao {
    List<GoodsAllDto> selectByF(Integer id);
}
