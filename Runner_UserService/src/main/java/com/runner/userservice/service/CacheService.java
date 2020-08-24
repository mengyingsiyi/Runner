package com.runner.userservice.service;

import com.runner.commons.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @paragram: runner
 * @author: wangyang
 * @create: 2020/8/20 16:42
 * @Description:
 */
@FeignClient("cacheserver")
public interface CacheService {
    @PostMapping(value = "/api/cache/getstr.do")
    String get(@RequestParam String key);

    @PostMapping("/api/cache/check.do")
    boolean check(@RequestParam String key);

    @PostMapping("/api/cache/del.do")
    boolean del(@RequestParam String key);

    @PostMapping(value = "/api/cache/savestr.do",consumes = MediaType.APPLICATION_JSON_VALUE)
    Result set(@RequestParam String key, @RequestParam long times, @RequestParam String value);

}
