package com.show.hystrix.example;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 启动类
 * @author show
 * @SpringCloudApplication 里面包含下面三个注解
 * - @SpringBootApplication Springboot项目启动注解
 * - @EnableDiscoveryClient 注册中心Client端启动注解
 * - @EnableCircuitBreaker  断路器注解
 */
@SpringCloudApplication
@EnableFeignClients
public class HystrixApplication {

    public static void main(String[] args) {

        SpringApplication.run(HystrixApplication.class, args);
    }

    /**
     * RestTemplate Bean 组件
     * @author show
     * @date 10:45 2019/6/11
     * @LoadBalanced 添加该注解，可以直接通过服务名找到对应的IP地址
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
