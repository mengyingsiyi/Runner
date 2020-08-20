package com.runner.entity.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apply {

  private int id;
  private String phone;
  private String target;
  private int aid;
}
