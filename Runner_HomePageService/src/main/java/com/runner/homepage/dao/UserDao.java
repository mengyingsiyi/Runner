package com.runner.homepage.dao;

import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/24  20:05
 */
@Repository
public interface UserDao {
    String findName(int uid);
}
