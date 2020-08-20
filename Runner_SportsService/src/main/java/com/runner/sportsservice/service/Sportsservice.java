package com.runner.sportsservice.service;

import com.runner.commons.dto.SportsDto.checkcoureseDto;
import com.runner.commons.dto.SportsDto.coursesDto;
import com.runner.commons.vo.R;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  14:46
 */
public interface Sportsservice {
    R<String> actiritys(String id);
    R<String> addcourses(String id);
    R<String> checkcourses(String phone);
}
