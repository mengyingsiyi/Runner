package com.runner.homepage.dao;

import com.runner.commons.dto.OssDto;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  13:48
 */
@Repository
public interface OssDao {
    int save(OssDto dto);
}
