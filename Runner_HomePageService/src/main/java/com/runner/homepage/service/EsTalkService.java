package com.runner.homepage.service;

import com.runner.commons.vo.R;
import com.runner.homepage.dto.EsTalkDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/25  19:56
 */
public interface EsTalkService {
    R saveBatch();

    R all();
}
