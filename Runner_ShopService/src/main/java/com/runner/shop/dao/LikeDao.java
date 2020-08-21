package com.runner.shop.dao;

import com.runner.commons.dto.LikeDto;
import com.runner.entity.pojo.Likes;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @Description:   点赞
 * @author: ZackJun
 * @date: 2020/8/20  17:26
 */
@Repository
public interface LikeDao {
        // 查询数据库 如果 点赞了 就直接取消
    List<Integer> select(LikeDto dto);
        // 取消点赞
        Integer update(LikeDto dto);
    // 新建立点赞
   Integer save(LikeDto dto);
        // 查询点赞总数
    int selectCount(Integer flag);

    int update1(LikeDto dto);

  List<Likes> selectByDto(LikeDto dto);
}
