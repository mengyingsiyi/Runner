package com.runner.shop.service;

import com.runner.commons.dto.ShopDto.ComDto;
import com.runner.commons.vo.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/25  19:49
 */
public interface CommentService {
    R save(ComDto dto, String token , MultipartFile...file);
}
