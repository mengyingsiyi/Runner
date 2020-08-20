package com.runner.homepage.service.impl;

import com.runner.commons.dto.TalkDto;
import com.runner.commons.vo.R;
import com.runner.homepage.dao.TalkDao;
import com.runner.homepage.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  10:53
 */
@Service
public class TalkServiceImpl implements TalkService {
    @Autowired
    private TalkDao dao;

    @Override
    public R save(TalkDto dto) {
        if (dao.save(dto) > 0){
            return R.ok();
        }
        return R.fail("发布动态失败");
    }
}
