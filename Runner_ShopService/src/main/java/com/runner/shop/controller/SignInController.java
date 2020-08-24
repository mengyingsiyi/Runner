package com.runner.shop.controller;

import com.runner.commons.vo.R;
import com.runner.shop.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/23  20:45
 */
@RestController
@RequestMapping("/server/qiandao/")
public class SignInController {
    @Autowired
    private SignService signService;

    @PostMapping("/qian")
    public R qiandao(@RequestParam String token){

        return signService.sign(token);
    }


}
