package com.runner.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.ShopDto.ComDto;
import com.runner.commons.dto.ShopDto.MQDto;
import com.runner.commons.util.CensorUtil;
import com.runner.commons.util.FileUtil;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.User;
import com.runner.shop.dao.CommentDao;
import com.runner.shop.dao.GoodsDao;
import com.runner.shop.dao.GoodsOrderDao;
import com.runner.shop.oss.OssCore;
import com.runner.shop.service.CacheServer;
import com.runner.shop.service.CommentService;
import com.runner.shop.service.OssService;
import com.runner.shop.util.RedissonUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/25  19:52
 */
@Service
public class CommentServiceImpl implements CommentService {
      @Autowired
      private CommentDao dao ;
        @Autowired
        private OssService service;
        @Autowired
        private CacheServer  cacheServer ;

        private RedissonUtil redissonUtil;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private GoodsOrderDao orderDao;

    @Override
    public R save(ComDto dto, String token, MultipartFile... file) {
        ComDto comDto = new ComDto();
        // 安全检查
        if (!StringUtil.checkStr(token)) {
            if (cacheServer.check(token)) {
                User user = getUser(token);
                if (null != user) {
                     //采用分布式锁
            redissonUtil.lock("runner-shop");
                    try {
                        // 将上传数组进行 便利
                    for (MultipartFile multipartFile : file) {
                        if (!multipartFile.isEmpty()) {

                                // 获取到上传的内容
                                byte[] data = multipartFile.getBytes();
                                // 这里需要百度提供的过滤 （没写。。。）
                                switch (dto.getType()) {
                                    case 1://照片
                                        R r = service.uploadImg(multipartFile);
                                        if (r.getCode().equals(1000)) {
                                            comDto.setComImgUrl(r.getData().toString());
                                        }
                                        break;
                                    case 2://视频
                                        R r1 = service.uploadImg(multipartFile);
                                        if (r1.getCode().equals(1000)) {
                                            comDto.setComVideoUrl(r1.getData().toString());
                                        }
                                        break;
                                    default:
                                        //   获得
                                        comDto.setComDesc(String.valueOf(data));
                                         break;
                                }


                        }


                    }

                      if ( dao.save(dto)>0){
                        //    修改订单状态
                          MQDto<Long> mqDto = new MQDto<Long>(dto.getComOrderId(),4,null);
                    // 直接发送消息 属于 订单完成
                    template.convertAndSend(null,"open:putong",mqDto);

                      }
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }finally {
                        redissonUtil.unlock("runner-shop");
                    }
                }
                return R.fail("请重新登录");
            }
            return R.fail("请先进行登录操作");
        }
        return R.fail("未知错误。。");
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
