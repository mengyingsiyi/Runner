package com.runner.homepage.dao;

import com.runner.commons.dto.PicDto;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  17:54
 */
@Repository
public interface PicDao {
    int save(PicDto dto);
}
