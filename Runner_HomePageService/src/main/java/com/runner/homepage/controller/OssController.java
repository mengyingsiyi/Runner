package com.runner.homepage.controller;

import com.runner.commons.vo.R;
import com.runner.homepage.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  14:13
 */
@RestController
@RequestMapping("/runner/server/oss")
public class OssController {
    @Autowired
    private OssService service;

    @PostMapping("/uploadImg.do")
    public R uploadImg(@RequestPart MultipartFile file){
        return service.uploadImg(file);
    }

    @PostMapping("/uploadVideo.do")
    public R uploadVideo(@RequestPart MultipartFile file, HttpServletRequest request){
        return service.uploadVideo(file,request);
    }
}
