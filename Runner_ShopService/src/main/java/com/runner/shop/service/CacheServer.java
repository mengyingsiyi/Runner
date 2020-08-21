package com.runner.shop.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/20  21:25
 */
@FeignClient(name = "cacheserver")
public interface CacheServer {
    @PostMapping("/api/cache/getstr.do")
    String get(@RequestParam String key);

    @PostMapping("/api/cache/check.do")
    boolean check(@RequestParam String key);
}
