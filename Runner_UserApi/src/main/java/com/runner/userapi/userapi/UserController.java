package com.runner.userapi.userapi;

import com.runner.commons.dto.UserCodeLoginDto;
import com.runner.commons.dto.UserLoginDto;
import com.runner.commons.dto.UserRegisterDto;
import com.runner.commons.vo.R;
import com.runner.userapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @paragram: Runner
 * @author: wangyang
 * @create: 2020/8/19 22:14
 * @Description:
 */
@RestController
@Api(tags = "用户相关接口")
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;


    @ApiOperation("查看手机号是否可用")
    @GetMapping("/selectByTel")
    public R selectByTel(@RequestParam String tel){
        return userService.selectByTel(tel);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public R register(@RequestBody UserRegisterDto registerDto) {
        System.out.println(registerDto);
        return userService.register(registerDto);
    }

    @ApiOperation("用户名密码登录")
    @PostMapping("/login")
    public R login(@RequestBody UserLoginDto loginDto) {
        return userService.login(loginDto);
    }

    @ApiOperation("验证码登录")
    @PostMapping("/loginCode")
    public R loginCode(@RequestBody UserCodeLoginDto codeLoginDto) {
        return userService.loginCode(codeLoginDto);
    }

    @ApiOperation("发送验证码")
    @PostMapping("/madeCode")
    public R madeCode(@RequestParam String tel) {
        System.out.println(tel);
        return userService.madeCode(tel);
    }
}
