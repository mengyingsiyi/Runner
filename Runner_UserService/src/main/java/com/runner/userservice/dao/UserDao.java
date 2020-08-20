package com.runner.userservice.dao;

import com.runner.commons.dto.UserRegisterDto;
import com.runner.entity.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @paragram: Runner
 * @author: wangyang
 * @create: 2020/8/19 21:46
 * @Description:
 */
public interface UserDao {
    User selectByTel(String tel);
    int register(User user);

}
