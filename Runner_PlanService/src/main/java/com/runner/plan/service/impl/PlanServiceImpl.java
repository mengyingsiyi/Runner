package com.runner.plan.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.GainMuscleDto;
import com.runner.commons.dto.PlanInfoDto;
import com.runner.commons.util.BeanUtilCopy;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.GainMuscle;
import com.runner.entity.pojo.PlanInfo;
import com.runner.entity.pojo.PlanInstrument;
import com.runner.entity.pojo.User;
import com.runner.plan.dao.GainMuscleDao;
import com.runner.plan.dao.PlanDao;
import com.runner.plan.dao.PlanInstrumentDao;
import com.runner.plan.service.CacheService;
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
    @Autowired
    private CacheService cacheService;


    @Override
    public R addOrUpdatePlan(PlanInfoDto planInfoDto, String token) {
        String key = SystemConstant.USER_TOKEN + token;
//        String s = restTemplate.postForObject("http://cacheserver/api/cache/getstr.do", key, String.class);
        User user = JSON.parseObject(StringUtil.jsonHandle(cacheService.get(key)), User.class);
//        User user = JSON.parseObject(s, User.class);
        System.err.println(user);
        if (user!=null && !"".equals(user)){
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
                if(planInfo.getChoiceInstrument() == 1){
                    //运动计划中添加运动器械
                    PlanInstrument planInstrument = new PlanInstrument();
                    planInstrument.setInstrumentName(planInfoDto.getInstrumentName());
                    planInstrument.setPlanInfoId(planInfoDto.getPlanInfoId());
                    instrumentDao.saveInstrument(planInstrument);
                }
                if (planInfo.getFitnessGoals() == 2){
                    //新增增肌部位
                    GainMuscle gainMuscle = new GainMuscle();
                    gainMuscle.setGainMusclePart(planInfoDto.getGainMusclePart());
                    gainMuscle.setPartStatus(planInfoDto.getPartStatus());
                    gainMuscle.setImproveStatus(planInfoDto.getImproveStatus());
                    gainMuscle.setPlanInfoId(planInfoDto.getPlanInfoId());

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
    public R findPlanById(Integer planInfoId, String token) {
        User user = JSON.parseObject(StringUtil.jsonHandle(cacheService.get(SystemConstant.USER_TOKEN+token)), User.class);
//        获取用户id
        if (planInfoId!=null && planInfoId!=0){
            PlanInfo planInfo = planDao.findPlanInfo(planInfoId, user.getUId());
            PlanInfoDto planInfoDto = BeanUtilCopy.copy(PlanInfoDto.class, planInfo);
            if (planInfo.getFitnessGoals()==2){
                    //根据planInfoId查询增肌内容
                GainMuscleDto gainMuscle = muscleDao.getGainMuscle(planInfoId);
                planInfoDto.setGainMusclePart(gainMuscle.getGainMusclePart());
                planInfoDto.setPartStatus(gainMuscle.getPartStatus());
                planInfoDto.setImproveStatus(gainMuscle.getImproveStatus());
//                PlanInfoDto planInfoDto1 = BeanUtilCopy.copy(planInfoDto.getClass(), gainMuscle);
            }
            if (planInfo.getChoiceInstrument()==1){
                //根据planInfoId查询对应的运动器械
                PlanInstrument instrument = instrumentDao.findInstrumentById(planInfoId);
                planInfoDto.setInstrumentName(instrument.getInstrumentName());
            }

            if (planInfoDto!=null){
                return R.ok("ok",planInfoDto);
            }else {
                return R.fail("查询运动详情信息失败");
            }
        }
        return R.fail("亲，你还未制定任何计划,赶紧制定一个吧");
    }
}
