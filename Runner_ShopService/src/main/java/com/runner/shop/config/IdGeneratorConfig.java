package com.runner.shop.config;

import com.runner.commons.util.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/24  19:27
 */
@Configuration
public class IdGeneratorConfig {


    @Bean
    public IdGenerator generator(){
        return new IdGenerator();
    }

}
