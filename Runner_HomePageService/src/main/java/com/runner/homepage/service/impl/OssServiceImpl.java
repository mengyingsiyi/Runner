package com.runner.homepage.service.impl;

import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.OssDto;
import com.runner.commons.dto.PicDto;
import com.runner.commons.dto.VideoDto;
import com.runner.commons.oss.OssSingleCore;
import com.runner.commons.util.CensorUtil;
import com.runner.commons.util.FileUtil;
import com.runner.commons.util.StringUtil;
import com.runner.commons.vo.R;
import com.runner.homepage.dao.OssDao;
import com.runner.homepage.dao.PicDao;
import com.runner.homepage.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  13:53
 */
@Service
public class OssServiceImpl implements OssService {
    @Autowired
    private OssDao dao;
    @Autowired
    private PicDao picDao;
    @Override
    public R uploadImg(MultipartFile file) {
        try {
            if (CensorUtil.checkImg(file.getBytes())) {
                String fn = FileUtil.rename(file.getOriginalFilename());
                String url = OssSingleCore.getInstance().upload(SystemConstant.BUCKET_NAME, fn, file.getBytes());
                if (StringUtil.checkStr(url)) {
                    //记录数据库
                    OssDto ossDto = new OssDto(SystemConstant.OSSTYPE_IMG, SystemConstant.BUCKET_NAME,fn);
                    dao.save(ossDto);
                    PicDto picDto = new PicDto();
                    picDto.setOssId(ossDto.getOid());
                    picDto.setPicUrl(url);
                    return R.ok(picDto);
                } else {
                    return R.fail("发生了错误");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.fail("审核不通过");
    }

    @Override
    public R uploadImgs(MultipartFile[] files,int tid) {
        for (MultipartFile file : files) {
            try {
                if (CensorUtil.checkImg(file.getBytes())) {
                    String fn = FileUtil.rename(file.getOriginalFilename());
                    String url = OssSingleCore.getInstance().upload(SystemConstant.BUCKET_NAME, fn, file.getBytes());
                    if (StringUtil.checkStr(url)) {
                        //记录数据库
                        OssDto ossDto = new OssDto(SystemConstant.OSSTYPE_IMG, SystemConstant.BUCKET_NAME,fn);
                        dao.save(ossDto);
                        PicDto picDto = new PicDto();
                        picDto.setOssId(ossDto.getOid());
                        picDto.setPicUrl(url);
                        picDto.setTalkId(tid);
                        picDao.save(picDto);
                        return R.ok();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return R.fail("未知错误");
    }

    @Override
    public R uploadVideo(MultipartFile file, HttpServletRequest request) {
        try {
            String fn = FileUtil.rename(file.getOriginalFilename());
            String url = OssSingleCore.getInstance().upload(SystemConstant.BUCKET_NAME, fn, file.getBytes());
            if (StringUtil.checkStr(url)) {
                //记录数据库
                OssDto ossDto = new OssDto(SystemConstant.OSSTYPE_VIDEO, SystemConstant.BUCKET_NAME, fn);
                dao.save(ossDto);
                VideoDto videoDto = new VideoDto();
                videoDto.setOssId(ossDto.getOid());
                videoDto.setVideoUrl(url);
                return R.ok(videoDto);
            } else {
                return R.fail("发生了错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.fail("审核不通过");
    }

}
