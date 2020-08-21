package com.runner.shop.service;

import com.runner.commons.vo.R;
import io.swagger.models.auth.In;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/21  20:46
 */
public interface GoodsAllService {
    R  selectByF(Integer id);
    R   selectGoodsById(Integer id);
}
