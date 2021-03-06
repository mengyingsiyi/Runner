package com.runner.homepage.dao;

import com.runner.commons.dto.homedto.CommentDto;
import com.runner.commons.dto.homedto.TalkCommentDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/21  18:47
 */
@Repository
public interface CommentDao {
    int save(@Param("dto") CommentDto dto, @Param("uid") int uid);
    List<CommentDto> findComment(int talkId);
    int selectCount(int talkId);
    List<TalkCommentDto> findComments(int talkId);
}
