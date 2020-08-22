package com.runner.plan.dao;

import com.runner.entity.pojo.PlanInstrument;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-21 22:07
 */
public interface PlanInstrumentDao {
    //根据训练详情id修改运动器械
    int updateInstrumentById(PlanInstrument planInstrument);
}
