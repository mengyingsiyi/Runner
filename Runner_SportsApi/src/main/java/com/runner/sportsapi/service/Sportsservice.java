package com.runner.sportsapi.service;

import com.runner.commons.dto.SportsDto.CoursesiDto;
import com.runner.commons.dto.SportsDto.addDto;
import com.runner.commons.dto.SportsDto.checkcoureseDto;
import com.runner.commons.vo.R;

import javax.servlet.http.HttpServletRequest;

public interface Sportsservice {
    R actirity(String id);
    R add(addDto addDto, HttpServletRequest request);
    R checkc(CoursesiDto coursesiDto, HttpServletRequest request);
}
