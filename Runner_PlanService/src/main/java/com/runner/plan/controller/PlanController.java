package com.runner.plan.controller;

import com.runner.commons.dto.PlanInfoDto;
import com.runner.commons.vo.R;
import com.runner.plan.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-20 15:37
 */
@RestController
@RequestMapping("api/plan/")
public class PlanController {
    @Autowired
    private PlanService planService;

    @PostMapping("saveplan.do")
    public R savePlan(@RequestBody PlanInfoDto planInfoDto, @RequestParam String token){
        return planService.addOrUpdatePlan(planInfoDto,token);
    }

    @GetMapping("findplan.do")
    public R findPlan(@RequestParam Integer planId,@RequestParam String token){
        return planService.findPlanById(planId,token);
    }

    @GetMapping("showplanhome.do")
    public R showPlanHome(@RequestParam Integer planId,@RequestParam String token){
        return planService.showPlanHome(planId,token);
    }

    @GetMapping("showallplan.do")
    public R showAllPlan(@RequestParam String token){
        return planService.showAllPlan(token);
    }
}
