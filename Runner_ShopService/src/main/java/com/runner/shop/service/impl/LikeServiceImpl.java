package com.runner.shop.service.impl;

import com.runner.commons.dto.LikeDto;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.Likes;
import com.runner.shop.dao.LikeDao;
import com.runner.shop.service.LIkeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/20  19:02
 */
@Service
public class LikeServiceImpl implements LIkeService {
    @Autowired
    private LikeDao dao;

    @Transactional
    @Override
    public R qiandao(LikeDto dto) {


         if (dto!=null){
             List<Likes> likes = dao.selectByDto(dto);
             if(likes==null){
                  if (dao.save(dto)>0){
                      int i = dao.selectCount(1);
                      return R.ok("当前赞数为:" + i, null);
                  }
              }else {
                 //  判断是否赞过
                 List<Integer> select = dao.select(dto);
                 System.err.println(select.get(0));
                 if (select.get(0) > 1) {
                     //如果赞过 直接修改状态
                     Integer update = dao.update(dto);
                     if (update > 0) {
                         int i = dao.selectCount(1);
                         return R.ok("点赞成功当前赞数为:" + i, null);
                     }
                 } else {
//                      dao.update1(dto);
                     List<Integer> select1 = dao.select(dto);
                     System.err.println("----->" + select1.get(0));
                     if (select1.get(0) < 0) {
                         int i = dao.selectCount(1);
                         dao.update1(dto);
                         return R.ok("点赞成功当前赞数为:" + i, null);
                     } else {
                         List<Integer> select2 = dao.select(dto);
                         System.err.println(select2.get(0) + "<-------");

                         if (select2.get(0) >= 0) {
                             int i = dao.selectCount(1);
                             dao.update1(dto);
                             return R.ok("当前赞数为:" + i, null);
                         } else {
                             List<Integer> select3 = dao.select(dto);
                             System.err.println(select3.get(0) + "<-------");
                             if (select3.get(0) >= 0) {
                                 int i = dao.selectCount(1);
                                 dao.update(dto);
                                 return R.ok("当前赞数为:" + i, null);
                             }
                         }
                     }

                 }
             }
              }

        return R.fail("不知名错误");
    }
}
