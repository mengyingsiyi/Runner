package com.runner.shop.service.impl;

import com.runner.commons.dto.ShopDto.GoodsCouponDto;
import com.runner.commons.dto.ShopDto.GoodsDto;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.GoodsImgUrl;
import com.runner.shop.dao.GoodsAllDao;
import com.runner.shop.dao.GoodsCouponDao;
import com.runner.shop.dao.GoodsDao;
import com.runner.shop.dao.ImgUrlDao;
import com.runner.shop.service.GoodsAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/21  20:46
 */
@Service
public class GoodsAllServiceImpl implements GoodsAllService {
    @Autowired
    private GoodsAllDao  dao;
    @Autowired
    private ImgUrlDao imgUrlDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsCouponDao couponDao;

    @Override
    public R selectByF(Integer id) {
           if (id==null && id<0){
               id=1;
           }
           return R.ok("ok",dao.selectByF(id));


    }
        // 通过id 查询出单个商品一些东西
    @Override
    public R selectGoodsById(Integer id) {
        if (id==null && id<0){
            id=1;
        }
        GoodsDto goodsDto = goodsDao.selectById(id);
         if (null!=goodsDto){
             // 查询出 所有图片
             List<GoodsImgUrl> goodsImgUrls = imgUrlDao.imgList(id);
             //  查询 出 所有的 优惠
             List<GoodsCouponDto> goodsCouponDtos = couponDao.selectCouponById(id);
             goodsDto.setCoupons(goodsCouponDtos);

             goodsDto.setUrl(goodsImgUrls);
             return R.ok("ok",goodsDto);
         }

      return R.fail("未知名错误--");
    }
}
