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
    //获取用户id


}
