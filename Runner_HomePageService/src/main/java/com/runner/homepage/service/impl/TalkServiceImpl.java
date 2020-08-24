package com.runner.homepage.service.impl;

import com.alibaba.fastjson.JSON;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.PicDto;
import com.runner.commons.dto.TalkDto;
import com.runner.commons.dto.VideoDto;
import com.runner.commons.dto.homedto.HomeTalkDto;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.User;
import com.runner.entity.pojo.Video;
import com.runner.homepage.dao.PicDao;
import com.runner.homepage.dao.TalkDao;
import com.runner.homepage.dao.VideoDao;
import com.runner.homepage.service.CacheService;
import com.runner.homepage.service.OssService;
import com.runner.homepage.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  10:53
 */
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

    /**
     * 发布动态接口
     * @param dto
     * @param file 上传的文件
     * @param token 用户令牌
     * @return
     */
    @Override
    public R save(TalkDto dto, MultipartFile file, String token) {
        if (StringUtil.checkStr(token)) {
            if (dto != null) {
                if (cacheService.check(SystemConstant.USER_TOKEN+token)) {
                    String userStr = cacheService.get(SystemConstant.USER_TOKEN+token);
                    User user = JSON.parseObject(userStr, User.class);
                    if (null != user) {
                        if (dao.save(dto,user.getUId()) > 0) {
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
                                            //存入图片
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

    /**
     * 推荐动态列表
     * @return
     */
    @Override
    public R findTalk() {
        List<HomeTalkDto> homeTalk = dao.findHomeTalk();
        if (homeTalk != null && homeTalk.size() > 0){
            return R.ok(homeTalk);
        }
        return R.fail("还没有找到动态哟");
    }
}
