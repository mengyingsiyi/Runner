package com.runner.homepage.service.impl;

import com.runner.commons.vo.R;
import com.runner.homepage.dao.OssDao;
import com.runner.homepage.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  13:53
 */
@Service
public class OssServiceImpl implements OssService {
    @Autowired
    private OssDao dao;
    @Override
    public R upload(MultipartFile file) {
        return null;
    }
}
