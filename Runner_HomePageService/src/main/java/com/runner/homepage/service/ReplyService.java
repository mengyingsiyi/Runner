package com.runner.homepage.service;

import com.runner.commons.dto.homedto.ReplyDto;
import com.runner.commons.vo.R;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/21  19:31
 */
public interface ReplyService {
    R save(ReplyDto dto,String token);
}
