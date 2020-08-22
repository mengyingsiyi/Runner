package com.runner.sportsservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.SportsDto.CoursesiDto;
import com.runner.commons.dto.SportsDto.addDto;
import com.runner.commons.dto.SportsDto.coursesDto;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.Actirity;
import com.runner.entity.pojo.User;
import com.runner.sportsservice.dao.SportsDao;
import com.runner.sportsservice.service.CacheService;
import com.runner.sportsservice.service.Sportsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class Sportsserviceimpl implements Sportsservice {

    @Autowired
    private SportsDao sportsDao;

    @Autowired
    private CacheService cacheService;
    @Override
    public R<String> actiritys(String id) {
        Actirity a =  sportsDao.selectactirity(id);
        String s = String.valueOf(a);
        return R.ok(s);
    }

    @Override
    public R addcourses(addDto addDto,HttpServletRequest request) {
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        System.err.println(token);
        //System.out.println(cacheService.get(SystemConstant.USER_TOKEN + token));
        User user = JSON.parseObject(StringUtil.jsonHandle(cacheService.get(SystemConstant.USER_TOKEN + token)), User.class);
        System.out.println("aaaaaaa"+user);
        String c = addDto.getId();
            coursesDto coursesDto = new coursesDto();
            coursesDto.setPhone(String.valueOf(user.getUTel()));
            String coursesDto1 = String.valueOf(sportsDao.selectcourses(coursesDto.getPhone()));
            if (coursesDto1 == null) {
                coursesDto.setPhone(coursesDto.getPhone());
                coursesDto.setCourse(c);
                sportsDao.addcourses(coursesDto);
                return R.ok("添加课程成功！");
            }
            String a = (coursesDto1 + c);
            coursesDto.setCourse(a);
            sportsDao.addcourses(coursesDto);
            return R.ok("添加课程成功！");
    }

    @Override
    public R<String> checkcourses(CoursesiDto coursesiDto, HttpServletRequest request) {
        User user = JSON.parseObject(cacheService.get(SystemConstant.USER_PHONE + request.getHeader(SystemConstant.TOKEN_HEADER)), User.class);
        System.out.println(user);

       return R.ok();
 }
}