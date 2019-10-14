package com.springboot.cloud.gateway.ribbonlb;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LBConfig {

/*    @Bean
    public IRule myRule()
    {
        return new IpRouterLBClientRule(); //自定义负载均衡规则
    }*/
}
