package com.runner.homepage.util;

import com.alibaba.fastjson.JSON;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.util.StringUtil;
import com.runner.entity.pojo.User;
import com.runner.homepage.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/22  10:01
 */
@Component
public class CacheUserUtil {
    @Autowired
    private CacheService cacheService;
    public User getUser(String token){
        if (cacheService.check(SystemConstant.USER_TOKEN+token)){
            String userStr = cacheService.get(SystemConstant.USER_TOKEN+token);
            if (StringUtil.checkStr(userStr)){
                return JSON.parseObject(userStr,User.class);
            }
            return null;
        }
        return null;
    }
}
