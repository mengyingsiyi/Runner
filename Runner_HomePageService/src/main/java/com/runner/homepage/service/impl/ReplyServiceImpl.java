package com.runner.homepage.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.homedto.ReplyDto;
import com.runner.commons.util.BeanUtilCopy;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.Reply;
import com.runner.entity.pojo.User;
import com.runner.homepage.dao.ReplyDao;
import com.runner.homepage.dao.UserDao;
import com.runner.homepage.service.CacheService;
import com.runner.homepage.service.ReplyService;
import com.runner.homepage.util.CacheUserUtil;
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
    private CacheUserUtil cacheUserUtil;
    @Autowired
    private UserDao userDao;

    @Override
    public R save(ReplyDto dto, String token) {
        User user = cacheUserUtil.getUser(token);
        if (null != user) {
            Reply reply = BeanUtilCopy.copy(Reply.class, dto);
            reply.setReplyUserId(user.getUId());
            reply.setUsername(user.getUNickname());
            String toUserName = userDao.findName(dto.getReplyToUserId());
            reply.setToUserName(toUserName);
            reply.setUsername(user.getUNickname());
            reply.setToUserHead(user.getUHead());
            if (dao.save(reply, user.getUId()) > 0) {
                return R.ok("回复成功");
            }
            return R.fail("回复失败");
        }
        return R.fail("登录之后才能回复");
    }
}
