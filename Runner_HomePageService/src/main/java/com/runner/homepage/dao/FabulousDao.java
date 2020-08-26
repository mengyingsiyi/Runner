package com.runner.homepage.dao;

import com.runner.commons.dto.homedto.FabulousDto;
import com.runner.commons.dto.homedto.FabulousUserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/22  9:54
 */
@Repository
public interface FabulousDao {
    int save(@Param("uid") int uid, @Param("dto") FabulousDto dto);
    int del(@Param("uid") int uid, @Param("talkId") int talkId);
    int selectCount(int talkId);
    List<FabulousUserDto> findByTalkId(int talkId);
}
