package com.runner.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-20 19:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanInfoDto {
    private Integer planInfoId;
    private String planName;
    private Integer planLevel;
    private Integer fitnessGoals;
    private Integer choiceRun;
    private Integer choiceInstrument;
    private Double inputHeight;
    private Double inputWeight;
    private Double weightGoals;
    private Integer weekExerciseDays;
    private Integer dayExerciseMinute;
    private Integer planTime;
    //运动器械
    private String instrumentName;
    //增肌目标
    private Integer gainMusclePart;
    private String partStatus;
    private String improveStatus;
}
