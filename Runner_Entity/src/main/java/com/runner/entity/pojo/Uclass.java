package com.runner.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Uclass {
    private int id;
    private int uid;//用户id
    private int cid;//课程id
}
