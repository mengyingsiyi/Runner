package com.runner.sportsapi.service;

import com.runner.commons.dto.SportsDto.AddRiding;
import com.runner.commons.dto.SportsDto.CoursesiDto;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.Running;
import com.runner.entity.pojo.Uactiriy;
import com.runner.entity.pojo.Uclass;

import javax.servlet.http.HttpServletRequest;

public interface Sportsservice {
    R actirity(String id);
    R add(Uclass uclass, HttpServletRequest request);
    R checkc(Uclass uclass, HttpServletRequest request);
    R addaciritys(Uactiriy uactiriy,HttpServletRequest request);
    R addrunning(Running running,HttpServletRequest request);
    R addriding(AddRiding addRiding,HttpServletRequest request);
}
