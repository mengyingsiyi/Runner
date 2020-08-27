package com.runner.plan.service;

import com.runner.commons.dto.PlanInfoDto;
import com.runner.commons.vo.R;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-20 15:33
 */
public interface PlanService {
    //添加或修改计划
    R addOrUpdatePlan(PlanInfoDto planInfoDto,String token);
    //获取用户id和计划id查询计划
    R findPlanById(Integer planId,String token);
    //显示计划主页
    R showPlanHome(Integer planId,String token );

    //显示全部计划
    R showAllPlan(String token);
}
