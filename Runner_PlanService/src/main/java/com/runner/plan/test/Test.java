package com.runner.plan.test;

import com.runner.commons.dto.PlanInfoDto;
import com.runner.commons.util.BeanUtilCopy;
import com.runner.entity.pojo.PlanInfo;
import org.springframework.beans.BeanUtils;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-21 20:58
 */
public class Test {
    public static void main(String[] args) {
        PlanInfoDto planInfoDto = new PlanInfoDto(1,"减脂",2,1,1,
                1,60.8,180.5,60.0,5,
                20,5,"跳绳",
                2,"瘦弱","匀称");
        PlanInfo planInfo = new PlanInfo();
        BeanUtils.copyProperties(planInfoDto,planInfo);
        System.out.println(planInfo);
//        PlanInfo planInfo = BeanUtilCopy.copy(PlanInfo.class, planInfoDto);
//        System.out.println(planInfoDto);
//        System.out.println(planInfo);
//        private String planName;
//        private Integer planLevel;
//        private Integer fitnessGoals;
//        private Integer choiceRun;
//        private Integer choiceInstrument;
//        private double inputHeight;
//        private double inputWeight;
//        private double weightGoals;
//        private Integer weekExerciseDays;
//        private Integer dayExerciseMinute;
//        private Integer planTime;
//        //运动器械
//        private String instrumentName;
//        private Integer planInfoId;
//        //增肌目标
//        private Integer gainMusclePart;
//        private String partStatus;
//        private String improveStatus;

    }
}
