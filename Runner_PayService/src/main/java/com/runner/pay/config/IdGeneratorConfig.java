package com.runner.pay.config;


import com.runner.commons.util.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-12 10:03
 */
@Configuration
public class IdGeneratorConfig {
    @Bean
    public IdGenerator createID(){

        return new IdGenerator();
    }
}
