package com.runner.sportsservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.SportsDto.UclassDto;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.*;
import com.runner.sportsservice.dao.SportsDao;
import com.runner.sportsservice.service.CacheService;
import com.runner.sportsservice.service.Sportsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public R addcourses(Uclass uclass, HttpServletRequest request) {
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
            if (token != null) {
                User user = JSON.parseObject(StringUtil.jsonHandle(cacheService.get(SystemConstant.USER_TOKEN + token)), User.class);
                uclass.setUid(user.getUId());
                if (sportsDao.seleonec(uclass).equals("0")) {
                uclass.setUid(user.getUId());
                sportsDao.addcourses(uclass);
                return R.ok("添加成功！");
                }
                return R.fail("已经添加过该活动！");
            }
            return R.fail("无效id，请重新登录");
    }

    @Override
    public R checkcourses(Uclass uclass, HttpServletRequest request) {
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        User user = JSON.parseObject(StringUtil.jsonHandle(cacheService.get(SystemConstant.USER_TOKEN + token)), User.class);
        System.err.println(sportsDao.selectcourses(user.getUId()));
        List<UclassDto> selectcourses = sportsDao.selectcourses(user.getUId());

        return R.ok(selectcourses);
 }

    @Override
    public R<String> addaciritys(Uactiriy uactiriy, HttpServletRequest request) {
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
            if (token != null) {
                User user = JSON.parseObject(StringUtil.jsonHandle(cacheService.get(SystemConstant.USER_TOKEN + token)), User.class);
                uactiriy.setUid(user.getUId());
                if (sportsDao.seleonea(uactiriy).equals("0")) {
                uactiriy.setUid(user.getUId());
                sportsDao.addaciritys(uactiriy);
                return R.ok("添加成功！");
                    }
                return R.fail("已经添加过该活动！");
            }
            return R.fail("无效id，请重新登录");
    }

    @Override
    public R addrunning(Running running, HttpServletRequest request) {
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        System.out.println(token);
        if (token != null) {
            User user = JSON.parseObject(StringUtil.jsonHandle(cacheService.get(SystemConstant.USER_TOKEN + token)), User.class);
            running.setPhone(user.getUTel());
            running.setPhone(String.valueOf(sportsDao.uprunning(running)));
            if (running.getPhone() == null) {
                running.setPhone(user.getUTel());
                sportsDao.addrunning(running);
                return R.ok("添加成功！");
            }
            System.out.println("aaaaa"+running);
            running.setFat(running.getFat());
            running.setOutdoor(running.getOutdoor());
            running.setTreadmill(running.getTreadmill());
            System.out.println("ssssssssss"+running);
            sportsDao.uprunning(running);

        }
        return R.fail("无效id，请重新登录");
        }

    }