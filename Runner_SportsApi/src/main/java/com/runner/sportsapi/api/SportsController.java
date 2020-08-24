package com.runner.sportsapi.api;
import com.runner.commons.dto.SportsDto.AddRiding;
import com.runner.commons.dto.SportsDto.CoursesiDto;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.Running;
import com.runner.entity.pojo.Uactiriy;
import com.runner.entity.pojo.Uclass;
import com.runner.sportsapi.service.Sportsservice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "用户服务相关操作")
@RestController
@RequestMapping("api/sports/")
public class SportsController {
    @Autowired
    private Sportsservice sportsservice;

    @GetMapping("actirity/{id}")
    @ApiOperation(value = "查询活动")
    public R check(@PathVariable String id){
        return sportsservice.actirity(id);
    }

    @PostMapping("add.do")
    @ApiOperation(value = "添加用户课程")
    public R add(@RequestBody Uclass uclass, HttpServletRequest request){
        return sportsservice.add(uclass,request);
    }

    @PostMapping("cheakc.do")
    @ApiOperation(value = "查询用户课程")
    public R cheakc(@RequestBody Uclass uclass,HttpServletRequest request){
        return sportsservice.checkc(uclass,request);
    }

    @PostMapping("addaciritys.do")
    @ApiOperation(value = "添加用户活动")
    public R addaciritys(@RequestBody Uactiriy uactiriy, HttpServletRequest request){
        return sportsservice.addaciritys(uactiriy,request);
    }

    @PostMapping("addrunning.do")
    @ApiOperation(value = "添加用户跑步里程")
    public R addrunning(@RequestBody Running running, HttpServletRequest request){
        return sportsservice.addrunning(running,request);
    }

    @PostMapping("addriding.do")
    @ApiOperation(value = "添加用户骑行里程")
    public R addriding(@RequestBody AddRiding addRiding, HttpServletRequest request){
        return sportsservice.addriding(addRiding,request);
    }

}