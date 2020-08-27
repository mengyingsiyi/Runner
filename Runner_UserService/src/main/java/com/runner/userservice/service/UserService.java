package com.runner.userservice.service;

import com.runner.cache.exception.CacheException;
import com.runner.commons.dto.UserCodeLoginDto;
import com.runner.commons.dto.UserLoginDto;
import com.runner.commons.dto.UserRegisterDto;
import com.runner.commons.dto.userDto.UpdatePasswordDto;
import com.runner.commons.vo.R;

/**
 * @paragram: Runner
 * @author: wangyang
 * @create: 2020/8/19 21:47
 * @Description:
 */
public interface UserService {
    R selectByTel(String tel);
    R register(UserRegisterDto registerDto);

    R login(UserLoginDto loginDto) throws CacheException;

    R loginCode(UserCodeLoginDto codeLoginDto);

    R madeCode(String tel);

    R logout(String token);

    R updatePassword(UpdatePasswordDto passwordDto);

    R getUserFromToken(String token);
}
