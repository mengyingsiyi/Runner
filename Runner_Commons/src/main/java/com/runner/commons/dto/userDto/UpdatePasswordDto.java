package com.runner.commons.dto.userDto;

import lombok.Data;

/**
 * @paragram: runner
 * @author: wangyang
 * @create: 2020/8/21 22:30
 * @Description:
 */
@Data
public class UpdatePasswordDto {
    private String uTel;
    private String uPassword;
    private String Code;
}
