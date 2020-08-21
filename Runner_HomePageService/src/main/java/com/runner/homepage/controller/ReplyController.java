package com.runner.homepage.controller;

import com.runner.commons.dto.homedto.CommentDto;
import com.runner.commons.dto.homedto.ReplyDto;
import com.runner.commons.vo.R;
import com.runner.homepage.service.CommentService;
import com.runner.homepage.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/21  18:59
 */
@RestController
@RequestMapping("/runner/server/reply")
public class ReplyController {
    @Autowired
    private ReplyService service;

    @PostMapping("/save.do")
    public R save(ReplyDto dto, String token){
        return service.save(dto,token);
    }
}
