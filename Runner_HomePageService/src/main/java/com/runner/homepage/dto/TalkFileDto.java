package com.runner.homepage.dto;

import com.runner.commons.dto.TalkDto;
import com.runner.entity.pojo.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/25  19:41
 */
@Data
public class TalkFileDto implements Serializable {
    private TalkDto talkDto;
    private MultipartFile[] files;
    private int uid;
}
