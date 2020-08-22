package com.runner.userapi.service;

import com.runner.commons.dto.UserCodeLoginDto;
import com.runner.commons.dto.UserLoginDto;
import com.runner.commons.dto.UserRegisterDto;
import com.runner.commons.dto.userDto.UpdatePasswordDto;
import com.runner.commons.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @paragram: Runner
 * @author: wangyang
 * @create: 2020/8/19 22:12
 * @Description:
 */
@FeignClient("runneruserservice")
public interface UserService {
    @GetMapping("/user/selectByTel")
    R selectByTel(@RequestParam String tel);

    @GetMapping("/user/register")
    R register(@RequestBody UserRegisterDto registerDto);

    @PostMapping("/user/login")
    R login(@RequestBody UserLoginDto loginDto);

    @PostMapping("/user/loginCode")
    R loginCode(@RequestBody UserCodeLoginDto codeLoginDto);

    @PostMapping("/user/madeCode")
    R madeCode(@RequestParam String tel);

    @PostMapping("/user/logout")
    R logout(@RequestParam String token);

    @PostMapping("/user/updatePassword")
    R updatePassword(@RequestBody UpdatePasswordDto passwordDto);
}
