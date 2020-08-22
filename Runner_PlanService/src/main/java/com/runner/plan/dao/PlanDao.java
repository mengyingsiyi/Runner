package com.runner.plan.dao;

import com.runner.entity.pojo.PlanInfo;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-20 15:33
 */
public interface PlanDao {
    //添加计划
    int addPlan(PlanInfo planInfo);

    //根据用户id查询查询计划相关信息
    int findPlanById(Integer userId);
    //根据用户id修改计划信息
    int updatePlan(PlanInfo planInfo);



}
