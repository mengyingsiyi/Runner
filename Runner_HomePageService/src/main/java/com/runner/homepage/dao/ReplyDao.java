package com.runner.homepage.dao;

import com.runner.commons.dto.homedto.ReplyDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/21  19:10
 */
@Repository
public interface ReplyDao {
    int save(@Param("dto") ReplyDto dto, @Param("uid") int uid);
}
