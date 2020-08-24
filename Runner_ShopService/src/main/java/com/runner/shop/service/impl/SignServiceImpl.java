package com.runner.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.dto.SignInDto;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.User;

import com.runner.shop.dao.SignInDao;
import com.runner.shop.service.CacheServer;
import com.runner.shop.service.SignService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/20  21:24
 */
@Service
public class SignServiceImpl implements SignService {
        @Autowired
        private SignInDao dao;
        @Autowired
        private CacheServer cacheServer;


    @Override
    public R sign(String token) {
        //   首先 判断 token 不能为空
        if (StringUtil.checkStr(token)) {
            User user = getUser(token);
            if (null != user) {
                //  直接
                Integer uId = user.getUId();
                SignInDto dto = dao.findCountDay(uId);

                if (null != dto) {
                    //     周期
                    Integer continueSign = dto.getContinueSign();
                    //连续签到的 总天数
                    Integer count = dto.getCount();
//                    if (continueSign <= 30) {
                        if (continueSign == 30) {
                            if (dao.updateContinueSign(uId, 1) > 0) {
                                dto.setContinueSign(1);

                                return R.ok("签到成功", dto);
                            }
//                        } else if (continueSign == 15) {
//
//                        } else if (continueSign == 30) {
//
//                        }

                    } else {
                        Date signTime = dto.getSignTime();
                        Date date = new Date();
                        // 获取相差的天数
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(signTime);
                        long timeInMillis1 = calendar.getTimeInMillis();
                        calendar.setTime(date);
                        long timeInMillis2 = calendar.getTimeInMillis();
                        long betweenDays = (timeInMillis2 - timeInMillis1) / (1000L * 3600L * 24L);
                        if (betweenDays == 0) {
                            continueSign++;
                            count++;
                            dao.updateContinueSign(uId, continueSign);
                            dto.setContinueSign(continueSign);
                            dto.setCount(count);
                            return R.ok("签到成功", dto);
                        } else {
                            continueSign = 1;
                            count++;
                            if (dao.updateContinueSign(uId, continueSign) > 0) {
                                dto.setCount(count);
                                dto.setContinueSign(continueSign);
                                return R.ok("签到成功", dto);
                            }
                        }
                    }
                } else {
                    //   新用户进行签到
                    if (dao.save(uId) > 0) {
                        dto = dao.findCountDay(uId);
                        return R.ok("签到成功", dto);
                    }
                }


            }


        }


        return R.fail("签到失败 ， 请传递令牌 ");
    }






    //检查token
    public User getUser(String token) {
        if (StringUtil.checkStr(token)) {
            if (cacheServer.check(token)) {
                String s = cacheServer.get(token);
                if (StringUtil.checkStr(s)) {
                    return JSON.parseObject(s, User.class);
                }
            }
        }
        return null;
    }




}
