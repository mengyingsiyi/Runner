package com.runner.shopapi;

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
 * @date: 2020/8/19  15:25
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //  禁止连接数据库
@EnableFeignClients  // 启用调用其他服务
@EnableDiscoveryClient // 发现和注册服务
public class ShopApiApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ShopApiApplication.class,args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ShopApiApplication.class);
    }
}
