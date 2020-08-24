package com.runner.homepage.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.homedto.ReplyDto;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.User;
import com.runner.homepage.dao.ReplyDao;
import com.runner.homepage.service.CacheService;
import com.runner.homepage.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/21  19:31
 */
@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyDao dao;
    @Autowired
    private CacheService cacheService;
    @Override
    public R save(ReplyDto dto, String token) {
        if (cacheService.check(SystemConstant.USER_TOKEN+token)){
            String userStr = cacheService.get(SystemConstant.USER_TOKEN + token);
            if (StringUtil.checkStr(userStr)){
                User user = JSON.parseObject(userStr, User.class);
                dto.setUserName(user.getUNickname());
                if (dao.save(dto,user.getUId()) > 0){
                    return R.ok("回复成功");
                }
                return R.fail("回复失败");
            }
        }
        return R.fail("登录之后才能回复");
    }
}
