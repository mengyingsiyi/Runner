package com.runner.homepage.service.impl;

import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.OssDto;
import com.runner.commons.oss.OssSingleCore;
import com.runner.commons.util.CensorUtil;
import com.runner.commons.util.FileUtil;
import com.runner.commons.util.StringUtil;
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
        try {
            if (CensorUtil.checkImg(file.getBytes())) {
                String fn = FileUtil.rename(file.getOriginalFilename());
                String url = OssSingleCore.getInstance().upload(SystemConstant.BUCKET_NAME, fn, file.getBytes());
                if (StringUtil.checkStr(url)) {
                    //记录数据库
                    dao.save(new OssDto(SystemConstant.OSSTYPE_IMG,SystemConstant.BUCKET_NAME,fn));
                    return R.ok(url);
                } else {
                    return R.fail("发生了错误");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.fail("审核不通过");
    }
}
