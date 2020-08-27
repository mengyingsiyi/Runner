package com.runner.commons.dto.ShopDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 作为 MQ统一返回值
 * @author: ZackJun
 * @date: 2020/8/24  20:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MQDto<T> implements Serializable {
    private long id;
    private int type;//类型
    private  T obj;
}
