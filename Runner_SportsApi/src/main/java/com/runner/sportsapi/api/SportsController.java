package com.runner.sportsapi.api;
import com.runner.commons.dto.SportsDto.checkcoureseDto;
import com.runner.commons.vo.R;
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

    @GetMapping("add/{id}")
    @ApiOperation(value = "添加课程")
    public R add(@PathVariable String id){
        return sportsservice.add(id);
    }

    @GetMapping("cheakc/{phone}")
    @ApiOperation(value = "查询课程")
    public R cheakc(@PathVariable String phone){
        return sportsservice.checkc(phone);
    }



}