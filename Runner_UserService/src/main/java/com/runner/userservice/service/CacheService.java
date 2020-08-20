package com.runner.userservice.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.runner.cache.exception.CacheException;
import com.runner.commons.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
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
    @PostMapping("/api/cache/savestr.do")
    String get(@RequestParam String key);

    @PostMapping("/api/cache/check.do")
    boolean check(@RequestParam String key);

    @PostMapping("/api/cache/del.do")
    boolean del(@RequestParam String key);

    @PostMapping("/api/cache/savestr.do")
    Result set(@RequestParam String key, @RequestParam long times, @RequestParam String value);

}
