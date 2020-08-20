package com.runner.homepage.service;

import com.runner.commons.dto.TalkDto;
import com.runner.commons.vo.R;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  10:53
 */
public interface TalkService {
    R save(TalkDto dto);
}
