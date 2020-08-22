package com.runner.sportsservice.service;

import com.runner.commons.dto.SportsDto.CoursesiDto;
import com.runner.commons.dto.SportsDto.addDto;
import com.runner.commons.dto.SportsDto.checkcoureseDto;
import com.runner.commons.dto.SportsDto.coursesDto;
import com.runner.commons.vo.R;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  14:46
 */
public interface Sportsservice {
    R<String> actiritys(String id);
    R<String>addcourses(addDto addDto,HttpServletRequest request);
    R<String> checkcourses(CoursesiDto coursesiDto, HttpServletRequest request);
}
