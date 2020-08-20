package com.runner.sportsapi.service;

import com.runner.commons.dto.SportsDto.checkcoureseDto;
import com.runner.commons.vo.R;

public interface Sportsservice {
    R actirity(String id);
    R add(String id);
    R checkc(String phone);
}
