package com.runner.userservice.dao;

import com.runner.commons.dto.UserInfoDto;
import com.runner.commons.dto.userDto.UpdatePasswordDto;
import com.runner.entity.pojo.User;


/**
 * @paragram: Runner
 * @author: wangyang
 * @create: 2020/8/19 21:46
 * @Description:
 */
public interface UserDao {

    User userInfo(String uTel);
    User selectByTel(String tel);
    int register(User user);

    int uodatePassword(UpdatePasswordDto passwordDto);

    int changeUserInfo(UserInfoDto userInfoDto);

}
