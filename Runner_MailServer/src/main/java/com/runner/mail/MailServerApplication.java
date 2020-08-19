package com.runner.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  14:02
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MailServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailServerApplication.class, args);
    }
}
