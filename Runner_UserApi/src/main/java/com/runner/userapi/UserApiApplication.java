package com.runner.userapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  14:36
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients  // 启用调用其他服务
@EnableDiscoveryClient // 发现和注册服务
public class UserApiApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(UserApiApplication.class);
    }
}
