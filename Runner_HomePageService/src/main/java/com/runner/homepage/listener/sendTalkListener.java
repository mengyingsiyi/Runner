package com.runner.homepage.listener;

import com.runner.commons.dto.PicDto;
import com.runner.commons.dto.TalkDto;
import com.runner.commons.dto.VideoDto;
import com.runner.commons.vo.R;
import com.runner.homepage.dao.PicDao;
import com.runner.homepage.dao.TalkDao;
import com.runner.homepage.dao.VideoDao;
import com.runner.homepage.dto.TalkFileDto;
import com.runner.homepage.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/25  20:30
 */
@Component
@RabbitListener(queues = "sendtalk")//监听队列
@Slf4j
public class sendTalkListener {
    @Autowired
    private TalkDao dao;
    @Autowired
    private OssService ossService;
    @Autowired
    private VideoDao videoDao;
    @RabbitHandler
    public void handler(TalkFileDto talkFileDto){
        log.info("------------------>mq接收到消息开始处理");
        MultipartFile[] files = talkFileDto.getFiles();
        int uid = talkFileDto.getUid();
        TalkDto dto = talkFileDto.getTalkDto();
        if (dao.save(dto, uid) > 0) {
            if (null != files) {
                switch (dto.getTalkType()) {
                    case 1:
                        ossService.uploadImgs(files, dto.getTid());
                    case 2:
                        R<VideoDto> r2 = ossService.uploadImg(files[0]);
                        if (r2.getCode() == 10000) {
                            //存入视频
                            VideoDto videoDto = r2.getData();
                            videoDto.setTalkId(dto.getTid());
                            videoDao.save(videoDto);
                        }
                    default:
                       log.info("没有插入数据");
                }
            }
        }
    }
}
