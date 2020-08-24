package com.runner.shop.controller;

import com.runner.commons.dto.LikeDto;
import com.runner.commons.vo.R;
import com.runner.shop.service.LIkeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/20  19:11
 */
@RestController
@RequestMapping("/server/like")
public class LIkeController {
    @Autowired
    private LIkeService service;


    @GetMapping("/dian")
    public R  like(LikeDto dto){
     return    service.qiandao(dto);
    }
}
