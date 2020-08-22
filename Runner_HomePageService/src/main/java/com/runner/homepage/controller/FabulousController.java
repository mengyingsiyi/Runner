package com.runner.homepage.controller;

import com.runner.commons.dto.homedto.FabulousDto;
import com.runner.commons.vo.R;
import com.runner.homepage.annotation.RequestJson;
import com.runner.homepage.service.FabulousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/22  17:41
 */
@RestController
@RequestMapping("/runner/server/fabulous")
public class FabulousController {
    @Autowired
    private FabulousService service;
    @PostMapping("/save")
    public R save(@RequestBody FabulousDto dto, @RequestParam String token){
        return service.save(dto,token);
    }
    @PostMapping("/del")
    public R del(@RequestParam int talkId, @RequestParam String token){
        return service.del(talkId, token);
    }
}
