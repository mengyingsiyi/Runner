package com.runner.homepage.service;

import com.runner.commons.dto.OssDto;
import com.runner.commons.vo.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  13:51
 */
public interface OssService {
    R uploadImg(MultipartFile file);
    R uploadImgs(MultipartFile[] files,int tid);
    R uploadVideo(MultipartFile file, HttpServletRequest request);
}
