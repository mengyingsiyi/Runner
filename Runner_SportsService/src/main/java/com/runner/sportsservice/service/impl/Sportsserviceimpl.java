package com.runner.sportsservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.SportsDto.AddRiding;
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
        Actirity a = sportsDao.selectactirity(id);
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
        if (token != null) {
            User user = JSON.parseObject(StringUtil.jsonHandle(cacheService.get(SystemConstant.USER_TOKEN + token)), User.class);
            running.setPhone(user.getUTel());
            if (sportsDao.selectphone(running.getPhone()).equals("0")) {
                sportsDao.addrunning(running);
                return R.ok();
            }
            Running a = sportsDao.selectall(running);
            int s = Integer.valueOf(running.getFat());
            int d = Integer.valueOf(a.getFat());
            int o = Integer.valueOf(running.getOutdoor());
            int o1 = Integer.valueOf(a.getOutdoor());
            int t = Integer.valueOf(running.getTreadmill());
            int t1 = Integer.valueOf(a.getTreadmill());
            a.setFat(String.valueOf(s + d));
            a.setOutdoor(String.valueOf(o + o1));
            a.setTreadmill(String.valueOf(t + t1));
            sportsDao.uprunning(a);
            return R.ok();
        }
        return R.fail("无效id，请重新登录");
    }

    @Override
    public R addriding(AddRiding addRiding, HttpServletRequest request) {
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        if (token != null) {
            User user = JSON.parseObject(StringUtil.jsonHandle(cacheService.get(SystemConstant.USER_TOKEN + token)), User.class);
            addRiding.setPhone(user.getUTel());
            if (sportsDao.selectphone1(addRiding.getPhone()).equals("0")) {
                addRiding.setPhone(user.getUTel());
                sportsDao.addriding(addRiding);
                return R.ok();
            }
            int a = Integer.valueOf(sportsDao.selectridingsall(addRiding.getPhone()));
            int s = Integer.valueOf(addRiding.getRidings());
            addRiding.setRidings(String.valueOf(a+s));
            sportsDao.upriding(addRiding);
        }
        return R.fail("无效id，请重新登录");
    }
}