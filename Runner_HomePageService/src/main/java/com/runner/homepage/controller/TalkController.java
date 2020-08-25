package com.runner.homepage.controller;

import com.runner.commons.dto.TalkDto;
import com.runner.commons.vo.R;
import com.runner.homepage.annotation.RequestJson;
import com.runner.homepage.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public R save(@RequestJson TalkDto dto, @RequestJson MultipartFile file, @RequestJson String token){
        return service.save(dto,file,token);
    }

    @PostMapping("/findHomeTalk.do")
    public R findHomeTalk(){
        return service.findTalk();
    }

    @GetMapping("/findTalkDetail.do")
    public R findTalkDetail(@RequestParam int talkId) {
        return service.findTalkDetail(talkId);
    }
}
