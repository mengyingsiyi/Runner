package com.runner.shop.dao;

import com.runner.commons.dto.SignInDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/20  21:21
 */
@Repository
public interface SignInDao {
    int save(@Param("uid") int uid);  //    直接保存 签到

    int updateUTime(@Param("uid") int uid); // 直接修改时间
    // 修改延续签到
    int updateContinueSign(@Param("uid") int uid, @Param("continueSign") int continueSign);
    // 通过用户id 直接获取到 与之相对应的内容
    SignInDto findCountDay(@Param("uid") int uid);
    // 直接 插入一条 数据
}
