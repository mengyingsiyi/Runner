package com.runner.plan.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.PlanInfoDto;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.GainMuscle;
import com.runner.entity.pojo.PlanInfo;
import com.runner.entity.pojo.PlanInstrument;
import com.runner.entity.pojo.User;
import com.runner.plan.dao.GainMuscleDao;
import com.runner.plan.dao.PlanDao;
import com.runner.plan.dao.PlanInstrumentDao;
import com.runner.plan.service.PlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-20 15:36
 */
@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanDao planDao;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GainMuscleDao muscleDao;
    @Autowired
    private PlanInstrumentDao instrumentDao;

    @Override
    public R addOrUpdatePlan(PlanInfoDto planInfoDto, String token) {
        String key = SystemConstant.USER_TOKEN + token;
        String s = restTemplate.postForObject("http://cacheserver/api/cache/getstr.do", key, String.class);
        User user = JSON.parseObject(s, User.class);
        Integer uId = user.getUId();
        PlanInfo planInfo = new PlanInfo();
        BeanUtils.copyProperties(planInfoDto, planInfo);
        planInfo.setUserId(uId);
        System.err.println(planInfo);
        if (planDao.findPlanById(uId) > 0) {
            //进行修改操作
            int updatePlanResult = planDao.updatePlan(planInfo);
            if (planInfo.getChoiceInstrument() == 1) {
                //修改运动机械
                PlanInstrument planInstrument = new PlanInstrument();
                planInstrument.setInstrumentName(planInfoDto.getInstrumentName());
                planInstrument.setPlanInfoId(planInfoDto.getPlanInfoId());
                int UpdateInstrumentResult = instrumentDao.updateInstrumentById(planInstrument);
            }
            if (planInfo.getFitnessGoals() == 2) {
                //增肌计划
                GainMuscle gainMuscle = new GainMuscle();
                gainMuscle.setGainMusclePart(planInfoDto.getGainMusclePart());
                gainMuscle.setPartStatus(planInfoDto.getPartStatus());
                gainMuscle.setImproveStatus(planInfoDto.getImproveStatus());
                gainMuscle.setPlanInfoId(planInfoDto.getPlanInfoId());
                //修改增肌目标
                int updateMuscleResult = muscleDao.updateGainMuscleById(gainMuscle);
            }
            if (updatePlanResult != 0) {
                return R.ok();
            } else {
                return R.fail("重新制定计划失败");
            }

        } else {
            //新增计划
            int addPlanResult = planDao.addPlan(planInfo);
            if (addPlanResult > 1) {
                return R.ok();
            } else {
                return R.fail("新增失败");
            }

        }
    }
}
