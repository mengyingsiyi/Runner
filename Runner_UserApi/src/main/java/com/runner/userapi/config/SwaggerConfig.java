package com.runner.userapi.config;

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
 * @paragram: keep
 * @author: wangyang
 * @create: 2020/8/7 16:04
 * @Description: swagger配置类
 */
@Configuration//托管spring
@EnableSwagger2//开启swagger功能
public class SwaggerConfig {
    //创建文档说明
    public ApiInfo createAI(){
        ApiInfo apiInfo=new ApiInfoBuilder().title("用户后端接口文档").
                description("用户接口信息，可以查看对应的接口信息和对接口进行测试").
                contact(new Contact("WY","http://www.baidu.com","18339951870@163.com")).build();
        return apiInfo;
    }

    //创建Swagger扫描信息
    @Bean
    public Docket createD(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(createAI()).select().
                apis(RequestHandlerSelectors.basePackage("com.runner.userapi.userapi")).build();
    }
}
