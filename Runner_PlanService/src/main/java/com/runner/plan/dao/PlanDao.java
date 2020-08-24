package com.runner.plan.dao;

import com.runner.commons.dto.PlanDto;
import com.runner.commons.dto.PlanInfoDto;
import com.runner.entity.pojo.Plan;
import com.runner.entity.pojo.PlanInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    int findPlanById(@Param("useId") Integer userId,@Param("planId") Integer planId);
    //根据用户id修改计划信息
    int updatePlan(PlanInfo planInfo);
    //根据用户id和计划id查询相关信息
    PlanInfo findPlanInfo(@Param("planId") Integer planId, @Param("userId") Integer userId);

    //查询计划首页信息
    PlanDto findPlanDto(@Param("UserId") Integer userId,@Param("planId") Integer PlanId);

    //查询全部计划
    List<Plan> findAllPlan(Integer userId);


}
