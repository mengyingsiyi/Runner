package com.runner.entity.pojo;

import lombok.Data;
@Data
public class PlanInfo {

  private long planInfoId;
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
  private long userId;
}
