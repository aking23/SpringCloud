package com.springboot.cloud.demos.feign.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    @Bean
    @LoadBalanced //Ribbon 是客户端负载均衡的工具；
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }

    @Bean
    public IRule myRule()
    {
        return new CustomeLBRule(); //自定义负载均衡规则
    }
}
