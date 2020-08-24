package com.runner.userservice.controller;

import com.runner.cache.exception.CacheException;
import com.runner.commons.dto.UserCodeLoginDto;
import com.runner.commons.dto.UserLoginDto;
import com.runner.commons.dto.UserRegisterDto;
import com.runner.commons.dto.userDto.UpdatePasswordDto;
import com.runner.commons.vo.R;
import com.runner.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @paragram: Runner
 * @author: wangyang
 * @create: 2020/8/19 21:52
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查验手机号是否注册过
     * @param tel 手机号
     * @return
     */
    @GetMapping("/selectByTel")
    public R selectByTel(@RequestParam String tel) {
        return userService.selectByTel(tel);
    }

    /**
     * 用户注册
     * @param registerDto 封装要注册用户的手机号、昵称、密码、性别
     * @return
     */
    @PostMapping("/register")
    public R register(@RequestBody UserRegisterDto registerDto) {
        return userService.register(registerDto);
    }

    /**
     * 用户名密码登录
     * @param loginDto  封装用户登录的手机号和密码
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody UserLoginDto loginDto) throws CacheException {
        return userService.login(loginDto);
    }

    /**
     * 验证码登录
     * @param codeLoginDto  封装用户登录的手机号和验证码
     * @return
     */
    @PostMapping("/loginCode")
    public R loginCode(@RequestBody UserCodeLoginDto codeLoginDto) {
        return userService.loginCode(codeLoginDto);
    }

    /**
     * 发送验证码
     * @param tel  接收验证码的手机号
     * @return
     */
    @PostMapping("/madeCode")
    public R madeCode(@RequestParam String tel) {
        return userService.madeCode(tel);
    }

    /**
     * 退出登录
     * @param token
     * @return
     */
    @PostMapping("/logout")
    public R logout(@RequestParam String token) {
        return userService.logout(token);
    }

    /**
     * 找回密码
     * @param passwordDto 封装的找回密码的dto， 手机号   新密码，  验证码
     * @return
     */
    @PostMapping("/updatePassword")
    public R updatePassword(@RequestBody UpdatePasswordDto passwordDto) {
        return userService.updatePassword(passwordDto);
    }

    /**
     * 从token中获取user对象
     * @param token
     * @return
     */
    @GetMapping("/getuserfromtoken")
    public R getUserFromToken(@RequestParam String token) {
        return userService.getUserFromToken(token);
    }
}
