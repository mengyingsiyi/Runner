package com.runner.homepage.service;

import com.runner.commons.dto.homedto.CommentDto;
import com.runner.commons.vo.R;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/21  18:55
 */
public interface CommentService {
    R save(CommentDto dto,String token);
}
