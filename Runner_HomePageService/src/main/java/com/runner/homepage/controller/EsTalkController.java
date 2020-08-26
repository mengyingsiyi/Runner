package com.runner.homepage.controller;

import com.runner.commons.vo.R;
import com.runner.entity.pojo.User;
import com.runner.homepage.service.CacheService;
import com.runner.homepage.service.EsTalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/25  20:17
 */
@RestController
public class EsTalkController {
    @Autowired
    private EsTalkService service;
    @Autowired
    private CacheService cacheService;
    @GetMapping("save.do")
    public R save(){
        return service.saveBatch();
    }
    @GetMapping("all.do")
    public R all(){
        User user = new User();
        user.setUId(8);
        user.setUNickname("1111");
        return service.all();
    }
}
