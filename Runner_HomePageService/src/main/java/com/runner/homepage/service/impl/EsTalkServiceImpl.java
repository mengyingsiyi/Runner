package com.runner.homepage.service.impl;

import com.runner.commons.dto.homedto.HomeTalkDto;
import com.runner.commons.util.BeanUtilCopy;
import com.runner.commons.vo.R;
import com.runner.homepage.dao.EsTalkDao;
import com.runner.homepage.dao.TalkDao;
import com.runner.homepage.dto.EsTalkDto;
import com.runner.homepage.service.EsTalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/25  19:57
 */
@Service
public class EsTalkServiceImpl implements EsTalkService {
    @Autowired
    private EsTalkDao dao;
    @Autowired
    private ElasticsearchRestTemplate template;
    @Autowired
    private TalkDao talkDao;
    @Override
    public R saveBatch() {
        List<EsTalkDto> dtos = new ArrayList<>();
        List<HomeTalkDto> homeTalk = talkDao.findHomeTalk();
        for (HomeTalkDto homeTalkDto : homeTalk) {
            EsTalkDto esTalkDto = BeanUtilCopy.copyDto(EsTalkDto.class, homeTalkDto);
            dtos.add(esTalkDto);
        }
        dao.saveAll(dtos);
        return R.ok();
    }

    @Override
    public R all() {
        return R.ok(dao.findAll());
    }
}
