package com.runner.shop.dao;

import com.runner.commons.dto.ShopDto.ComDto;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/25  19:48
 */
@Repository
public interface CommentDao {
    int save(ComDto dto);
}
