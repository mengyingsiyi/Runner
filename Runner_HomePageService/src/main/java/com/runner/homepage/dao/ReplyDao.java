package com.runner.homepage.dao;

import com.runner.commons.dto.homedto.ReplyDto;
import com.runner.entity.pojo.Reply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/21  19:10
 */
@Repository
public interface ReplyDao {
    int save(@Param("reply") Reply reply, @Param("uid") int uid);
}
