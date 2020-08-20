package com.runner.homepage.service;

import com.runner.commons.vo.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  13:51
 */
public interface OssService {
    R upload(MultipartFile file);
}
