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
    private long planInfoId;
    private String planName;
    private long planLevel;
    private long fitnessGoals;
    private long choiceRun;
    private long choiceInstrument;
    private Double inputHeight;
    private Double inputWeight;
    private Double weightGoals;
    private long weekExerciseDays;
    private long dayExerciseMinute;
    private long planTime;
    //运动器械
    private String instrumentName;
    //增肌目标
    private Integer gainMusclePart;
    private String partStatus;
    private String improveStatus;
}
