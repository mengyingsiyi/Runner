package com.runner.server.service;

import com.runner.commons.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @paragram: runner
 * @author: wangyang
 * @create: 2020/8/24 19:28
 * @Description:
 */
@FeignClient("runneruserservice")
public interface UserService {

    @PostMapping(value = "/user/getuserfromtoken",consumes = MediaType.APPLICATION_JSON_VALUE)
    R getUserFromToken(@RequestParam String token);
}
