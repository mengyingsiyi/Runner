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
    int save(@Param("uid") int uid);
    int updateUTime(@Param("uid") int uid);
    int updateContinueSign(@Param("uid") int uid, @Param("continueSign") int continueSign);
    SignInDto findCountDay(@Param("uid") int uid);
}
