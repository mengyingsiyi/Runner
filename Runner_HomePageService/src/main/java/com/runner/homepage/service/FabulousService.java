package com.runner.homepage.service;

import com.runner.commons.dto.homedto.FabulousDto;
import com.runner.commons.vo.R;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/22  9:59
 */
public interface FabulousService {
    R save(FabulousDto dto,String token);
    R del(int talkId,String token);
    R findFabulousUser(int talkId);
}
