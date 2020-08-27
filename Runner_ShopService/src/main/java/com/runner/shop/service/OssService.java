package com.runner.shop.service;

import com.runner.commons.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:   调用服务上传 服务
 * @author: ZackJun
 * @date: 2020/8/25  19:54
 */
@FeignClient(name = "runnerhomepageservice")
public interface OssService {

    @PostMapping("/runner/server/oss/uploadImg.do")
    public R uploadImg(@RequestPart MultipartFile file);

    @PostMapping("/runner/server/oss/uploadVideo.do")
    public R uploadVideo(@RequestPart MultipartFile file, HttpServletRequest request);

}
