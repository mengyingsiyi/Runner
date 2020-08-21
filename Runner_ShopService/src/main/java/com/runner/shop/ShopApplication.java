package com.runner.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  15:17
 */
@SpringBootApplication
@EnableTransactionManagement //开启事务
@EnableDiscoveryClient // 注册与发现服务
@EnableFeignClients // 调用
@MapperScan("com.runner.shop.dao")  // 扫描 dao成
public class ShopApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ShopApplication.class);
    }
}
