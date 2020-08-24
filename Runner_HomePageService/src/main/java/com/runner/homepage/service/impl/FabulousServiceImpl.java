package com.runner.homepage.service.impl;

import com.runner.commons.dto.homedto.FabulousDto;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.User;
import com.runner.homepage.dao.FabulousDao;
import com.runner.homepage.dao.TalkDao;
import com.runner.homepage.service.CacheService;
import com.runner.homepage.service.FabulousService;
import com.runner.homepage.util.CacheUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/22  9:59
 */
@Service
public class FabulousServiceImpl implements FabulousService {
    @Autowired
    private FabulousDao dao;
    @Autowired
    private CacheUserUtil cacheUserUtil;
    @Autowired
    private TalkDao talkDao;

    /**
     * 点赞接口
     *
     * @param dto
     * @param token
     * @return
     */
    @Override
    public R save(FabulousDto dto, String token) {
        User user = cacheUserUtil.getUser(token);
        if (null != user) {
            if (talkDao.updateFCount(dto.getTalkId(),1) > 0) {
                if (dao.save(user.getUId(), dto) > 0) {
                    return R.ok("点赞成功");
                }
            }
            return R.fail("点赞失败");
        }
        return R.fail("你还未登陆哟");
    }

    @Override
    public R del(int talkId, String token) {
        User user = cacheUserUtil.getUser(token);
        if (null != user) {
            talkDao.updateFCount(talkId,-1);
            dao.del(user.getUId(), talkId);
            return R.ok();
        }
        return R.fail("取消点赞失败");
    }
}
