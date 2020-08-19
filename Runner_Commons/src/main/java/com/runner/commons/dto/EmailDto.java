package com.runner.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  21:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    private String title;
    private String content;
    private String recemail;
}
