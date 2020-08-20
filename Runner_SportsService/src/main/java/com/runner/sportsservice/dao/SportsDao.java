package com.runner.sportsservice.dao;

import com.runner.commons.dto.SportsDto.coursesDto;
import com.runner.entity.pojo.Actirity;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsDao {
    //查询活动
    Actirity selectactirity(String id);
    //查询用户有多少课程
    String selectcourses(String phone);
    //根据手机号添加课程
    int addcourses(coursesDto coursesDto);


}
