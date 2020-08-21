package com.runner.commons.dto;

import lombok.Data;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-20 19:13
 */
@Data
public class PlanInfoDto {
    private String planName;
    private long planLevel;
    private long fitnessGoals;
    private long choiceRun;
    private long choiceInstrument;
    private double inputHeight;
    private double inputWeight;
    private double weightGoals;
    private long weekExerciseDays;
    private long dayExerciseMinute;
    private long planTime;
    //运动器械
    private String instrumentName;
    private long planInfoId;
    //增肌目标
    private long gainMusclePart;
    private String partStatus;
    private String improveStatus;
}
