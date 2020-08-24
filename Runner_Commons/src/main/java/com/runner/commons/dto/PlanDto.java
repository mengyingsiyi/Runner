package com.runner.commons.dto;

import lombok.Data;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-24 22:17
 */
@Data
public class PlanDto {
    private Integer planId;
    private String planType;
    private Integer planTime;
    private Integer weekExerciseDays;
    private Integer dayExerciseMinute;
    private Integer planLevel;

}
