package com.runner.sportsservice.dao;

import com.runner.commons.dto.SportsDto.AddRiding;
import com.runner.commons.dto.SportsDto.UclassDto;
import com.runner.entity.pojo.Actirity;
import com.runner.entity.pojo.Running;
import com.runner.entity.pojo.Uactiriy;
import com.runner.entity.pojo.Uclass;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportsDao {
    //查询活动
    Actirity selectactirity(String id);
    //查询用户有多少课程
    List<UclassDto> selectcourses(Integer id);
    //根据用户id为用户添加课程
    int addcourses(Uclass uclass);
    //查询是否有添加过的课程
    String seleonec(Uclass uclass);
    //添加用户活动
    int addaciritys(Uactiriy uactiriy);
    //查询是否有添加过的课程
    String seleonea(Uactiriy uactiriy);
    //添加跑步记录
    int addrunning(Running running);
    //查询是否有运动记录
    String selectphone(String phone);
    //根据手机号添加跑步记录
    int uprunning (Running running);

    Running selectall(Running running);
    //添加骑行记录
    int addriding (AddRiding addRiding);
    //查询是否有运动记录
    String selectphone1(String phone);
    //根据手机号添加骑行记录
    int upriding (AddRiding addRiding);

    String selectridingsall(String ridings);

}
