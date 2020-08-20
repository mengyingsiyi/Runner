package com.runner.homepage.dao;

import com.runner.commons.dto.PicDto;
import com.runner.commons.dto.VideoDto;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  17:54
 */
@Repository
public interface VideoDao {
    int save(VideoDto dto);
}
