package com.runner.homepage.dao;

import com.runner.commons.dto.PicDto;
import com.runner.commons.dto.TalkDto;
import com.runner.commons.dto.homedto.HomeTalkDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  10:52
 */
public interface TalkDao {
    int save(@Param("dto") TalkDto dto, @Param("uid") int uid);

    List<HomeTalkDto> findHomeTalk();

    int updateFCount(@Param("talkId") int talkId, @Param("num") int num);
}
