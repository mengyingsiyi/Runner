package com.runner.entity.pojo;

import lombok.Data;
@Data
public class PlanInfo {

  private Integer planInfoId;
  private String planName;
  private Integer planLevel;
  private Integer fitnessGoals;
  private Integer choiceRun;
  private Integer choiceInstrument;
  private double inputHeight;
  private double inputWeight;
  private double weightGoals;
  private Integer weekExerciseDays;
  private Integer dayExerciseMinute;
  private Integer planTime;
  private Integer userId;
  private Integer planId;
}
