package com.show.hystrix.example;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

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

}
