package com.runner.homepage.dao;

import com.runner.commons.dto.homedto.CommentDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/21  18:47
 */
@Repository
public interface CommentDao {
    int save(@Param("dto") CommentDto dto, @Param("uid") int uid);
}
