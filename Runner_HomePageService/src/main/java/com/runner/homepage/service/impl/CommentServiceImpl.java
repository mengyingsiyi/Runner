package com.runner.homepage.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.homedto.CommentDto;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.User;
import com.runner.homepage.dao.CommentDao;
import com.runner.homepage.service.CacheService;
import com.runner.homepage.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/21  18:55
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao dao;
    @Autowired
    private CacheService cacheService;
    @Override
    public R save(CommentDto dto, String token) {
        if (cacheService.check(SystemConstant.USER_TOKEN+token)){
            String userStr = cacheService.get(SystemConstant.USER_TOKEN + token);
            if (StringUtil.checkStr(userStr)){
                User user = JSON.parseObject(userStr, User.class);
                dto.setCommentUserName(user.getUNickname());
                if (dao.save(dto,user.getUId()) > 0){
                    return R.ok("评论成功");
                }
                return R.fail("评论失败");
            }
        }
        return R.fail("登录之后才能评论");
    }
}
