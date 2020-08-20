package com.runner.homepage.controller;

import com.runner.commons.dto.TalkDto;
import com.runner.commons.vo.R;
import com.runner.homepage.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  10:54
 */
@RestController
@RequestMapping("/runner/server/home")
public class TalkController {
    @Autowired
    private TalkService service;
    @PostMapping("/save.do")
    public R save(@RequestBody TalkDto dto){
        return service.save(dto);
    }
}
