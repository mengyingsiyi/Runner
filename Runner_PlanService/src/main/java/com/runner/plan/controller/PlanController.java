package com.runner.plan.controller;

import com.runner.commons.dto.PlanInfoDto;
import com.runner.commons.vo.R;
import com.runner.plan.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public R savePlan(PlanInfoDto planInfoDto,String token){
        return planService.addOrUpdatePlan(planInfoDto,token);
    }
}
