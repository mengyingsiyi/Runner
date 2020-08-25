package com.runner.homepage.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.homedto.CommentDto;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.User;
import com.runner.homepage.dao.CommentDao;
import com.runner.homepage.dao.TalkDao;
import com.runner.homepage.service.CacheService;
import com.runner.homepage.service.CommentService;
import com.runner.homepage.util.CacheUserUtil;
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
    @Autowired
    private CacheUserUtil cacheUserUtil;
    @Autowired
    private TalkDao talkDao;

    /**
     * 发布评论接口
     *
     * @param dto
     * @param token
     * @return
     */
    @Override
    public R save(CommentDto dto, String token) {
        User user = cacheUserUtil.getUser(token);
        if (null != user) {
            if (dao.save(dto, user.getUId()) > 0) {
                //修改动态表的评论数量
                talkDao.updateCCount(dto.getTalkId());
                return R.ok("评论成功");
            }
            return R.fail("评论失败");
        }
        return R.fail("登录之后才能评论");
    }
}
