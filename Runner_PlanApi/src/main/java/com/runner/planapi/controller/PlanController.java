package com.runner.planapi.controller;

import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.PlanInfoDto;
import com.runner.commons.vo.R;
import com.runner.planapi.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-22 09:49
 */
@Api(tags = "计划相关操作")
@RestController
@RequestMapping("api/plan/")
public class PlanController{
    @Autowired
    private PlanService service;

    @ApiOperation("制定计划")
    @PostMapping("savepaln.do")
    public R savePlan(@RequestBody PlanInfoDto planInfoDto, HttpServletRequest request){
       return service.savePlan(planInfoDto,request.getHeader(SystemConstant.TOKEN_HEADER));
    }

    @ApiOperation("显示计划详情")
    @GetMapping("showpaln.do")
    public R showPlan(@RequestParam Integer planId,HttpServletRequest request){
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        return service.showPlan(planId,token);
    }


    @ApiOperation("计划基本详情")
    @GetMapping("showplanhome.do")
    public R showPlanHome(@RequestParam Integer planId,HttpServletRequest request){
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        return service.showPlanHome(planId,token);
    }


    @ApiOperation("全部计划")
    @GetMapping("showallplan.do")
    public R showAllPlan(HttpServletRequest request){
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        return service.showAllPlan(token);
    }




}
