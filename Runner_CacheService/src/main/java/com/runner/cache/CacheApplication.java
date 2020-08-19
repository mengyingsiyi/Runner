package com.runner.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  11:35
 */
@SpringBootApplication
@EnableDiscoveryClient   //  注册和发现服务
public class CacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class,args);
    }
}
