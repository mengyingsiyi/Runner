package com.runner.plan.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.GainMuscleDto;
import com.runner.commons.dto.PlanDto;
import com.runner.commons.dto.PlanInfoDto;
import com.runner.commons.util.BeanUtilCopy;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.*;
import com.runner.plan.dao.GainMuscleDao;
import com.runner.plan.dao.PlanDao;
import com.runner.plan.dao.PlanInstrumentDao;
import com.runner.plan.service.CacheService;
import com.runner.plan.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-20 15:36
 */
@Slf4j
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
    @Autowired
    private CacheService cacheService;


    @Override
    public R addOrUpdatePlan(PlanInfoDto planInfoDto, String token) {
        String key = SystemConstant.USER_TOKEN + token;
//        String s = restTemplate.postForObject("http://cacheserver/api/cache/getstr.do", key, String.class);
        User user = JSON.parseObject(StringUtil.jsonHandle(cacheService.get(key)), User.class);
//        User user = JSON.parseObject(s, User.class);
//        System.err.println(user);
        if (user!=null && !"".equals(user)){
            Integer uId = user.getUId();
            PlanInfo planInfo=BeanUtilCopy.copyDto(PlanInfo.class,planInfoDto );
            planInfo.setUserId(uId);
//            System.err.println(planInfo);
            Integer planId = planInfoDto.getPlanId();
            if (planDao.findPlanById(uId,planId) > 0) {

                //进行修改操作
                int updatePlanResult = planDao.updatePlan(planInfo);
                if (planInfo.getChoiceInstrument() == 1) {
                    //修改运动机械
                    PlanInstrument planInstrument = new PlanInstrument();
                    planInstrument.setInstrumentName(planInfoDto.getInstrumentName());
                    planInstrument.setPlanInfoId(planDao.findPlanInfo(planId,uId).getPlanInfoId());
                    int UpdateInstrumentResult = instrumentDao.updateInstrumentById(planInstrument);
                }

                if (planInfo.getFitnessGoals() == 2) {
                    //增肌计划
                    GainMuscle gainMuscle = new GainMuscle();
                    gainMuscle.setGainMusclePart(planInfoDto.getGainMusclePart());
                    gainMuscle.setPartStatus(planInfoDto.getPartStatus());
                    gainMuscle.setImproveStatus(planInfoDto.getImproveStatus());
                    gainMuscle.setPlanInfoId(planDao.findPlanInfo(planId,uId).getPlanInfoId());
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
//                System.err.println("planInfo主键回填"+planInfo.getPlanInfoId());

                if (planInfo.getFitnessGoals() == 2){
                    if(planInfo.getChoiceInstrument() == 1){
                        //运动计划中添加运动器械
                        PlanInstrument planInstrument = new PlanInstrument();
                        planInstrument.setInstrumentName(planInfoDto.getInstrumentName());
                        planInstrument.setPlanInfoId(planInfo.getPlanInfoId());
                        instrumentDao.saveInstrument(planInstrument);
                    }
                    //新增增肌部位
                    GainMuscle gainMuscle = new GainMuscle();
                    gainMuscle.setGainMusclePart(planInfoDto.getGainMusclePart());
                    gainMuscle.setPartStatus(planInfoDto.getPartStatus());
                    gainMuscle.setImproveStatus(planInfoDto.getImproveStatus());
                    gainMuscle.setPlanInfoId(planInfo.getPlanInfoId());
                    muscleDao.saveGainMuscle(gainMuscle);
//                    System.err.println(gainMuscle);

                }
                if (addPlanResult > 0) {
                    return R.ok();
                } else {
                    return R.fail("新增失败");
                }

            }
        }
        return R.fail("用户信息不存在");
    }

    @Override
    public R findPlanById(Integer planId, String token) {
//        System.err.println(token);
        User user = JSON.parseObject(StringUtil.jsonHandle(cacheService.get(SystemConstant.USER_TOKEN+token)), User.class);
//        System.err.println(user);
//        获取用户id
        if (planId!=null && planId!=0){
//            System.err.println(planId+"=======>"+user.getUId());
            PlanInfo planInfo = planDao.findPlanInfo(planId, user.getUId());
//            System.err.println(planInfo);
            PlanInfoDto planInfoDto = BeanUtilCopy.copyDto(PlanInfoDto.class, planInfo);
//            PlanInfoDto planInfoDto = new PlanInfoDto();
//            planInfoDto.setPlanName(planInfo.getPlanName());
//            planInfoDto.setPlanLevel(planInfo.getPlanLevel());
//            planInfoDto.setChoiceRun(planInfo.getChoiceRun());
//            planInfoDto.setWeightGoals(planInfo.getWeightGoals());
//            planInfoDto.setInputHeight(planInfo.getInputHeight());
//            planInfoDto.setInputWeight(planInfo.getInputHeight());
//            planInfoDto.setDayExerciseMinute(planInfo.getDayExerciseMinute());
//            planInfoDto.setWeekExerciseDays(planInfo.getWeekExerciseDays());
//            planInfoDto.setPlanTime(planInfo.getPlanTime());
            planInfoDto.setChoiceInstrument(planInfo.getChoiceInstrument());
            planInfoDto.setFitnessGoals(planInfo.getFitnessGoals());
//            System.err.println(planInfoDto);
            if (planInfo.getFitnessGoals() !=null &&  planInfo.getFitnessGoals()==2){
                if (planInfo.getChoiceInstrument()==1){
                    //根据planInfoId查询对应的运动器械
                    PlanInstrument instrument = instrumentDao.findInstrumentById(planInfo.getPlanInfoId());
//                    System.err.println(instrument);
                    planInfoDto.setInstrumentName(instrument.getInstrumentName());
                }
                    planInfoDto.setChoiceInstrument(planInfo.getChoiceInstrument());

                    //根据planInfoId查询增肌内容
//                log.info("计划详情的id"+"----->"+planInfo.getPlanInfoId());
                GainMuscleDto gainMuscle = muscleDao.getGainMuscle(planInfo.getPlanInfoId());
//                System.err.println(gainMuscle);
                planInfoDto.setGainMusclePart(gainMuscle.getGainMusclePart());
                planInfoDto.setPartStatus(gainMuscle.getPartStatus());
                planInfoDto.setImproveStatus(gainMuscle.getImproveStatus());
//                PlanInfoDto planInfoDto1 = BeanUtilCopy.copy(planInfoDto.getClass(), gainMuscle);
            }else{
                planInfoDto.setFitnessGoals(planInfo.getFitnessGoals());
            }
            if (planInfoDto!=null){
                return R.ok("ok",planInfoDto);
            }else {
                return R.fail("查询运动详情信息失败");
            }
        }
        return R.fail("亲，你还未制定任何计划,赶紧制定一个吧");
    }

    @Override
    public R showPlanHome(Integer planId, String token) {
        User user = JSON.parseObject(StringUtil.jsonHandle(cacheService.get(SystemConstant.USER_TOKEN+token)), User.class);
        if (user!=null){
            PlanDto planDto = planDao.findPlanDto(user.getUId(), planId);
            System.out.println("单个计划基本信息---->"+planDto);
            return R.ok("ok",planDto);
        }else {
            return R.fail("亲你还未制定任何计划赶快制定计划吧");
        }

    }

    @Override
    public R showAllPlan(String token) {
        User user = JSON.parseObject(StringUtil.jsonHandle(cacheService.get(SystemConstant.USER_TOKEN+token)), User.class);
        if (user != null){
            List<Plan> planList = planDao.findAllPlan(user.getUId());
            System.out.println(planList);
            return R.ok("全部计划",planList);
        }else {
            return R.fail("登录失效");
        }

    }
}
