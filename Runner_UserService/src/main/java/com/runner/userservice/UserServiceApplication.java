package com.runner.userservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  14:09
 */
@SpringBootApplication
@EnableDiscoveryClient // 注册与发现服务
@EnableFeignClients // 调用
@MapperScan("com.runner.userservice.dao")
public class UserServiceApplication extends SpringBootServletInitializer {
public static void main(String[] args) {

    SpringApplication.run(UserServiceApplication.class,args);
}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(UserServiceApplication.class);
    }
}