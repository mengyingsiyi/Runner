package com.runner.shop.dao;

import com.runner.entity.pojo.GoodsImgUrl;
import lombok.Data;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/21  21:22
 */
@Repository
public interface ImgUrlDao {
    List<GoodsImgUrl> imgList(Integer id);
}
