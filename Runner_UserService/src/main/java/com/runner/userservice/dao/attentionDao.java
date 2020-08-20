package com.runner.userservice.dao;

import com.runner.commons.dto.userDto.attentionDto;
import com.runner.entity.pojo.attention;

import java.util.List;

/**
 * @Description: TODO
 * @Author FCJ
 * @Date 2020/8/20 16:15
 * @Version V1.0
 */
public interface attentionDao {
    //添加用户的关注
    int addByFlayA(attention attention);
    //添加词条的关注
    int addByFlayB(attention attention);
    //话题的
    int addByFlayC(attention attention);
    //查询我关注的账户
    List<attentionDto> findAllByFlagA(Integer my_uid);
    //查询我关注的词条
    //查询我关注的话题
    //查询我的粉丝
    List<attentionDto> findAllByID(Integer my_uid);
}
