package com.runner.homepage.service.impl;

import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.PicDto;
import com.runner.commons.dto.TalkDto;
import com.runner.commons.dto.VideoDto;
import com.runner.commons.dto.homedto.TalkCommentDto;
import com.runner.commons.dto.homedto.TalkDetailDto;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.User;
import com.runner.homepage.dao.*;
import com.runner.homepage.dto.TalkFileDto;
import com.runner.homepage.service.CacheService;
import com.runner.homepage.service.EsTalkService;
import com.runner.homepage.service.OssService;
import com.runner.homepage.service.TalkService;
import com.runner.homepage.util.CacheUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  10:53
 */
@Slf4j
@Service
public class TalkServiceImpl implements TalkService {
    @Autowired
    private TalkDao dao;

    @Autowired
    private OssService ossService;

    @Autowired
    private PicDao picDao;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private CacheUserUtil cacheUserUtil;

    @Autowired
    private FabulousDao fabulousDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private EsTalkService esTalkService;
    @Autowired
    public RabbitTemplate template;
    /**
     * 发布动态接口
     *
     * @param dto
     * @param file  上传的文件
     * @param token 用户令牌
     * @return
     */
    @Override
    public R save(TalkDto dto, MultipartFile file, String token) {
        if (StringUtil.checkStr(token)) {
            if (dto != null) {
                if (cacheService.check(SystemConstant.USER_TOKEN + token)) {
                    User user = cacheUserUtil.getUser(token);
                    if (null != user) {
                        if (dao.save(dto, user.getUId()) > 0) {
                            if (!file.isEmpty()) {
                                switch (dto.getTalkType()) {
                                    case 1:
                                        R<PicDto> r = ossService.uploadImg(file);
                                        if (r.getCode() == 10000) {
                                            //存入图片
                                            PicDto picDto = r.getData();
                                            picDto.setTalkId(dto.getTid());
                                            picDao.save(picDto);
                                            return R.ok();
                                        }
                                        return R.fail("上传文件出错了");
                                    case 2:
                                        R<VideoDto> r2 = ossService.uploadImg(file);
                                        if (r2.getCode() == 10000) {
                                            //存入视频
                                            VideoDto videoDto = r2.getData();
                                            videoDto.setTalkId(dto.getTid());
                                            videoDao.save(videoDto);
                                            return R.ok();
                                        }
                                        return R.fail("上传文件出错了");
                                    default:
                                        return R.fail("暂不支持的类型");
                                }
                            }
                            return R.fail("传入的文件不能为空");
                        }
                    }
                }
                return R.fail("发表动态失败了");
            }
        }
        return R.fail("未知错误");
    }
    @Override
    public R save(TalkDto dto, MultipartFile[] files, String token) {
        User user = cacheUserUtil.getUser(token);
        if (null != user){
            TalkFileDto talkFileDto = new TalkFileDto();
            talkFileDto.setFiles(files);
            talkFileDto.setUid(user.getUId());
            talkFileDto.setTalkDto(dto);
            log.info("发送消息到mq");
            template.convertSendAndReceive("sendtalk",talkFileDto);
            return R.ok("发动动态成功");
        }
        return R.fail("登录后才能发表动态");
    }


    /**
     * 推荐动态列表
     *
     * @return
     */
    @Override
    public R findTalk() {
        R all = esTalkService.all();
        if (all.getCode() == 10000){
            return all;
        }
        return R.fail("还没有找到动态哟");
    }

    @Override
    public R findTalkDetail(int talkId) {
        //分布查询动态的详情
        //1.连接用户、动态、图片查出一条动态的对象
        TalkDetailDto detail = dao.findDetail(talkId);
        if (null != detail) {
            List<TalkCommentDto> comments = commentDao.findComments(talkId);
            if (null != comments && comments.size() > 0) {
                detail.setCommentDtos(comments);
                return R.ok(detail);
            }
        }
        return R.fail("未找到动态详情");
    }
}
