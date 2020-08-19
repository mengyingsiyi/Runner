package com.runner.mail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: OpenMain
 * @description
 * @author:
 * @create: 2020-07-29 07:59
 **/
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    public ApiInfo creatAi(){
        ApiInfo apiInfo=new ApiInfoBuilder().title("Runner项目")
                .description("项目接口文档")
                .contact(new Contact("Feri","http:www.baidu.com","xing")).build();
        return apiInfo;
    }
    @Bean
    public Docket creatD(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(creatAi())
                .select().apis(RequestHandlerSelectors.basePackage("com.runner.mail.controller")).build();
    }
}
