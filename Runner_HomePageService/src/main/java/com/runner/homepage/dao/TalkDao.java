package com.runner.homepage.dao;

import com.runner.commons.dto.TalkDto;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  10:52
 */
public interface TalkDao {
    int save(@Param("dto") TalkDto dto, @Param("uid") int uid);
}
