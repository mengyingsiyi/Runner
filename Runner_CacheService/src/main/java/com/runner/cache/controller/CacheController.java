package com.runner.cache.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.runner.cache.exception.CacheException;
import com.runner.cache.service.CacheService;
import com.runner.commons.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/20  16:21
 */
@Slf4j
@RestController
@RequestMapping("/api/cache")
public class CacheController {
    @Autowired
    private CacheService service;

    //实现常用的操作
    @SentinelResource(fallback = "saveError")
    @PostMapping("/savestr.do")
    public Result save(@RequestParam String key, @RequestParam long times, @RequestParam String value) throws CacheException {
        System.out.println("存了");
        return Result.setR(service.set(key, times, value));
    }
    //降级方法
    public Result saveError(String key, long times, String value)throws CacheException {
        log.error("缓存服务崩溃");
        return Result.error();
    }

    @PostMapping("/getstr.do")
    public String get(@RequestParam String key) throws CacheException {
        return service.get(key);
    }

    @PostMapping("/check.do")
    public boolean check(@RequestParam String key) throws CacheException {
        return service.contains(key);
    }
    @PostMapping("/del.do")
    public boolean del(@RequestParam String key) throws CacheException {
        return service.del(key);
    }
}
