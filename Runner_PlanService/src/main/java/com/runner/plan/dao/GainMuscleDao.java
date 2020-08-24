package com.runner.plan.dao;

import com.runner.commons.dto.GainMuscleDto;
import com.runner.entity.pojo.GainMuscle;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-21 21:37
 */
public interface GainMuscleDao {
    //根据详情表id修改增肌目标信息
    int updateGainMuscleById(GainMuscle gainMuscle);

    //新增增肌计划
    int saveGainMuscle(GainMuscle gainMuscle);

    //根据详情表id查询增肌目标信息
    GainMuscleDto getGainMuscle(Integer planInfoId);
}
