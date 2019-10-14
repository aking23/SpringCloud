package com.springboot.cloud.demos.feign;

import com.springboot.cloud.demos.feign.config.ConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
//在启动该微服务的时候就能去加载我们自定义的Ribbon配置类，从而使配置生效：
//@RibbonClient(name="producer", configuration=ConfigBean.class)
public class FeignConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerApplication.class, args);
    }
}
