package com.runner.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.User;

import com.runner.shop.dao.SignInDao;
import com.runner.shop.service.CacheServer;
import com.runner.shop.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/20  21:24
 */
public class SignServiceImpl implements SignService {
        @Autowired
        private SignInDao dao;
        @Autowired
        private CacheServer cacheServer;


    @Override
    public R sign(String token) {









        return null;
    }






    //检查token
    public User getUser(String token) {
        if (StringUtil.checkStr(token)) {
            if (cacheServer.check(token)) {
                String s = cacheServer.get(token);
                if (StringUtil.checkStr(s)) {
                    return JSON.parseObject(s, User.class);
                }
            }
        }
        return null;
    }




}
