package com.runner.sportsservice.service;

import com.runner.commons.dto.SportsDto.CoursesiDto;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.Running;
import com.runner.entity.pojo.Uactiriy;
import com.runner.entity.pojo.Uclass;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  14:46
 */
public interface Sportsservice {
    //查询所有活动
    R<String> actiritys(String id);
    //添加用户课程
    R<String>addcourses(Uclass uclass, HttpServletRequest request);
    //查询用户参与的课程
    R<List>checkcourses(Uclass uclass, HttpServletRequest request);
    //添加用户参与的活动
    R<String>addaciritys(Uactiriy uactiriy, HttpServletRequest request);
    //添加跑步信息
    R addrunning(Running running,HttpServletRequest request);
}
