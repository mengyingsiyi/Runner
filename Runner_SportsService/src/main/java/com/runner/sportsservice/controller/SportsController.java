package com.runner.sportsservice.controller;

import com.runner.commons.dto.SportsDto.CoursesiDto;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.Running;
import com.runner.entity.pojo.Uactiriy;
import com.runner.entity.pojo.Uclass;
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

    @PostMapping("addcourses.do")
    public R addcourses(@RequestBody Uclass uclass, HttpServletRequest request){
        return sportsservice.addcourses(uclass,request);
    }

    @PostMapping("cheakcourses.do")
    public R checkcourses(@RequestBody Uclass uclass,HttpServletRequest request){
        return sportsservice.checkcourses(uclass,request);
    }

    @PostMapping("addaciritys.do")
    public R addaciritys(@RequestBody Uactiriy uactiriy, HttpServletRequest request){
        return sportsservice.addaciritys(uactiriy,request);
    }

    @PostMapping("addrunning.do")
    public R addrunning(@RequestBody Running running, HttpServletRequest request){
        return sportsservice.addrunning(running,request);
    }

}

