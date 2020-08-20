package com.runner.homepage.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/20  20:24
 */
@FeignClient("cacheserver")
public interface CacheService {
    @PostMapping("/api/cache/getstr.do")
    String get(@RequestParam String key);
    @PostMapping("/api/cache/check.do")
    boolean check(@RequestParam String key);
}
