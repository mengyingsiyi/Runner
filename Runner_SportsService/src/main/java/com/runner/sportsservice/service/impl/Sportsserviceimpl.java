package com.runner.sportsservice.service.impl;

import com.runner.commons.dto.SportsDto.checkcoureseDto;
import com.runner.commons.dto.SportsDto.coursesDto;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.Actirity;
import com.runner.sportsservice.dao.SportsDao;
import com.runner.sportsservice.service.Sportsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Sportsserviceimpl implements Sportsservice {

    @Autowired
    private SportsDao sportsDao;
    @Override
    public R<String> actiritys(String id) {
        Actirity a =  sportsDao.selectactirity(id);
        String s = String.valueOf(a);
        return R.ok(s);
    }

    @Override
    public R addcourses(String id) {
        if(id.length() == 1) {
            coursesDto coursesDto = new coursesDto();
            coursesDto.setPhone(String.valueOf(123));
            String coursesDto1 = String.valueOf(sportsDao.selectcourses(coursesDto.getPhone()));
            if (coursesDto1 == null) {
                coursesDto.setPhone(coursesDto.getPhone());
                coursesDto.setCourse(id);
                sportsDao.addcourses(coursesDto);
                return R.ok("添加课程成功！");
            }
            String a = (coursesDto1 + id);
            coursesDto.setCourse(a);
            sportsDao.addcourses(coursesDto);
            return R.ok("添加课程成功！");
        }
        return R.fail("一次只能添加一门课程");
    }

    @Override
    public R<String> checkcourses(String phone) {
        String coursesDto1 = String.valueOf(sportsDao.selectcourses(phone));
        String[] split = coursesDto1.split("");
       for(int i = 0;i<split.length;i++){
            String a =  (split[i]);
            return R.ok(a);
        }
        return null;
    }
}
