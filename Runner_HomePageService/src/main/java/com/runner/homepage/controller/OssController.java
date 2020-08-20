package com.runner.homepage.controller;

import com.runner.commons.vo.R;
import com.runner.homepage.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/upload.do")
    public R upload(@RequestPart MultipartFile file){
        return service.upload(file);
    }
}
