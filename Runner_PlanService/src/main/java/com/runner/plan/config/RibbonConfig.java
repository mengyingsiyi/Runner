package com.runner.plan.config;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: runner
 * @description
 * @author:
 * @create: 2020-07-29 09:19
 **/
@Configuration
public class RibbonConfig {
    @Bean
    @LoadBalanced//启动Ribbon的负载均衡
    public RestTemplate creatTem(){
        return new RestTemplate();
    }
    @Bean
    public IRule creatIReul(){
        //return new RandomRule();//随机策略
        //return new ZoneAvoidanceRule();//区域感知就近访问
        //return new WeightedResponseTimeRule();//权重响应时间策略
        //return new BestAvailableRule();//并发量最小策略
        return new AvailabilityFilteringRule();//可用性策略，会选择可用性服务提供者
        //return new RoundRobinRule();//轮询策略

    }
}
