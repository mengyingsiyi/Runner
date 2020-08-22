package com.runner.plan.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-22 16:29
 */
@FeignClient("cacheserver")
public interface CacheService {
    @PostMapping(value = "/api/cache/getstr.do",consumes = MediaType.APPLICATION_JSON_VALUE)
    String get(@RequestParam String key);
    @PostMapping(value = "/api/cache/check.do",consumes = MediaType.APPLICATION_JSON_VALUE)
    boolean check(@RequestParam String key);
}
