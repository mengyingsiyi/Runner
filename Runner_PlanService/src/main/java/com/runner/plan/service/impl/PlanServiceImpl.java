package com.runner.plan.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.PlanInfoDto;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.User;
import com.runner.plan.dao.PlanDao;
import com.runner.plan.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-20 15:36
 */
@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanDao planDao;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public R addOrUpdatePlan(PlanInfoDto planInfoDto, String token) {
        String key = SystemConstant.USER_TOKEN+token;
        String s = restTemplate.postForObject("http://cacheserver/api/cache/getstr.do", key, String.class);
        User user = JSON.parseObject(s, User.class);
        user.getUId();
        return null;
    }




}
