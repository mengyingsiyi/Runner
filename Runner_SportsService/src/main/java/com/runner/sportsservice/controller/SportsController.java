package com.runner.sportsservice.controller;

import com.runner.commons.dto.SportsDto.checkcoureseDto;
import com.runner.commons.vo.R;
import com.runner.sportsservice.service.Sportsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CourseController
 * @Description TODO
 * @Author 袁帅
 * @Date 2020/8/5 20:57
 * @Version 1.0
 */
@RestController
@RequestMapping("sports/")
public class SportsController {

    @Autowired
    private Sportsservice sportsservice;

    //查询活动
    @GetMapping("actiritys.do")
    public R actirity(@RequestParam String id){
        return sportsservice.actiritys(id);
    }

    @GetMapping("addcourses.do")
    public R addcourses(@RequestParam String id){
        return sportsservice.addcourses(id);
    }

    @GetMapping("cheakcourses.do")
    public R checkcourses(@RequestParam String phone){
        return sportsservice.checkcourses(phone);
    }

}

