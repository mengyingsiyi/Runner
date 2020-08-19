package com.runner.homepage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  15:58
 */
@SpringBootApplication
@EnableDiscoveryClient // 注册与发现服务
@EnableFeignClients // 调用
@MapperScan("com.runner.homepage.dao")
public class HomePageApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {

        SpringApplication.run(HomePageApplication.class, args);


    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(HomePageApplication.class);
    }
}
